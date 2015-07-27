package net.tuxv.miwaykotlin.presenters

import android.util.Log
import net.tuxv.miwaykotlin.models.Favourite
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.models.Time
import net.tuxv.miwaykotlin.services.RealmDatabaseService
import net.tuxv.miwaykotlin.utils.BusTimesService
import net.tuxv.miwaykotlin.views.NextTimesFragment
import org.joda.time.DateTime
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList
import java.util.concurrent.TimeUnit

// TODO: Use a delayed observable to change the time values when time passes
class NextTimesPresenter(val route : Route, val stop : Stop) {
    val TAG = "NextTimesPresenter"

    private var view: NextTimesFragment? = null
    private var error : Throwable? = null
    private val nextTimes : ArrayList<Time> = ArrayList()

    init {
        BusTimesService().busTimesApi
                .getTimes(route.num!!, route.direction!!, stop.stopId!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { response -> response?.data }
                .subscribe(object : Observer<List<Time>?> {
                    override fun onNext(times: List<Time>?) {
                        nextTimes.addAll(times!!.asSequence().filter { t ->filterPastTimes(t)})
                        publish()
                        Log.d(TAG, "Populating next times for the first time")

                        Observable.from(times)
                                .subscribeOn(Schedulers.newThread())
                                .filter { t -> filterPastTimes(t) }
                                .limit(3)
                                .buffer(3)
                                .delay(60, TimeUnit.SECONDS)
                                .repeat()
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(object : Observer<List<Time>?> {
                                    override fun onError(e: Throwable?) {
                                        throw UnsupportedOperationException()
                                    }

                                    override fun onNext(times: List<Time>?) {
                                        Log.d(TAG, "Updating next times")
                                        nextTimes.addAll(times)
                                        publish();
                                        nextTimes.clear();
                                    }

                                    override fun onCompleted() {
                                        Log.e(TAG, "onComplete called. But should run forever")
                                    }

                                })
                    }

                    override fun onCompleted() {
                        Log.d(TAG, "Completed Times")
                    }

                    override fun onError(e: Throwable?) {
                        Log.d(TAG, e?.getMessage())
                    }

                })
    }


    fun attachView(view : NextTimesFragment?) {
        this.view = view
        publish()
    }

    private fun publish() {
        if(view != null && nextTimes.size() > 0) {
            view?.onContentLoaded(nextTimes)
        } else if(view != null && error != null) {
            // TODO: work on pullToRefresh
            view?.onError(error!!, false)
        }
    }

    private fun filterPastTimes(time : Time) : Boolean {
        val dt = DateTime()
        val hour = dt.getHourOfDay()
        val minute = dt.getMinuteOfHour()

        return when {
            time.hour!! > hour -> true
            time.hour!! == hour && time.minute!! > minute -> true
            else -> false
        }
    }
}

