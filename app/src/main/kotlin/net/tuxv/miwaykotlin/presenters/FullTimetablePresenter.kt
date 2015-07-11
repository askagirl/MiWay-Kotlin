package net.tuxv.miwaykotlin.presenters

import android.util.Log
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.views.FullTimetableFragment

import android.util.Log
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.models.Time
import net.tuxv.miwaykotlin.utils.BusTimesProvider
import net.tuxv.miwaykotlin.views.NextTimesFragment
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

// TODO: Use a delayed observable to change the time values when time passes
class FullTimetablePresenter(val route : Route, val stop : Stop) {
    val TAG = "NextTimesPresenter"

    private var view: NextTimesFragment? = null
    private var error : Throwable? = null
    private var items : ArrayList<Time>? = null

    init {
        Observable.defer {() ->
            Observable.just(BusTimesProvider.getFullTimetable(route.number, route.direction, stop.number, 1))
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ArrayList<Time>>{
                    override fun onNext(times: ArrayList<Time>?) {
                        items = ArrayList(times!!)
                        publish()
                    }

                    override fun onError(e: Throwable?) {
                        Log.d(TAG, "Error: " + e?.getMessage())
                    }

                    override fun onCompleted() {
                        Log.d(TAG, "Completed")
                    }
                })
    }


    fun attachView(view : FullTimetableFragment?) {
        this.view = view
        publish()
    }

    private fun publish() {
        if(view != null && items != null) {
            view!!.onContentLoaded(items!!)
        } else if(view != null && error != null) {
            // TODO: work on pullToRefresh
            view!!.onError(error!!, false)
        }
    }

}

