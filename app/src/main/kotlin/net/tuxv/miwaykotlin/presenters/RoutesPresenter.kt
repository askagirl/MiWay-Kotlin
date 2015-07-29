package net.tuxv.miwaykotlin.presenters

import android.util.Log
import net.tuxv.miwaykotlin.views.RoutesFragment
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.utils.BusTimesService
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

class RoutesPresenter() {
    val TAG = "RoutesPresenter"

    var items : ArrayList<Route>? = null
    var error : Throwable? = null
    var view: RoutesFragment? = null

    init {
        BusTimesService().busTimesApi
                .getRoutes()
                .retry(3)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response -> response?.data }
                .subscribe(object : Observer<List<Route>?> {
                    override fun onNext(routes : List<Route>?) {
                        items = ArrayList(routes)
                        publish()
                    }

                    override fun onCompleted() {
                        Log.d(TAG, "Completed")
                    }

                    override fun onError(e: Throwable?) {
                        Log.d(TAG, "ERROR" + e?.getMessage())
                    }
                })
    }

    fun attachView(view : RoutesFragment?) {
        this.view = view
        publish()
    }

    private fun publish() {
        if(view != null && items != null) {
            view!!.onContentLoaded(items!!)
        } else if(view != null && error != null){
            // TODO: work on pullToRefresh
            view!!.onError(error!!, false)
        }
    }

}
