package net.tuxv.miwaykotlin.presenters

import android.util.Log
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.utils.BusTimesService
import net.tuxv.miwaykotlin.views.StopsActivity
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

class StopsPresenter(val route : Route) {
    val TAG = "StopsPresenter"

    var items : ArrayList<Stop>? = null
    var error : Throwable? = null
    var view : StopsActivity? = null

    // TODO: Implement
    init {
        BusTimesService().busTimesApi
                .getStops(route.id!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response -> response?.items }
                .subscribe(object : Observer<List<Stop>?> {
                    override fun onNext(stops : List<Stop>?) {
                        items = ArrayList(stops)
                        publish()
                    }

                    override fun onCompleted() {
                        Log.d(TAG, "Completed Stops")
                    }

                    override fun onError(e: Throwable?) {
                        Log.d(TAG, e?.getMessage())
                    }
                })
    }

    fun attachView(view : StopsActivity?) {
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
