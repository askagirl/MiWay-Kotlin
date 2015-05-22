package net.tuxv.miwaykotlin.presenters

import android.util.Log
import net.tuxv.miwaykotlin.RoutesFragment
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.RouteResponse
import net.tuxv.miwaykotlin.utils.BusTimesApi
import net.tuxv.miwaykotlin.utils.BusTimesService
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers
import java.util.ArrayList

/**
 * Created by yasith on 15-05-21.
 */

class RoutesPresenter() {
    var items : ArrayList<Route>? = null
    var error : Throwable? = null

    var routesView : RoutesFragment? = null
    val TAG = "RoutesPresenter"

    init {
        // TODO
        BusTimesService().busTimesApi
                .getRoutes()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
//                .map(object : Func1<RouteResponse,List<Route>>{
//                    override fun call(response : RouteResponse?): List<Route>? {
//                        return response?.items
//                    }
//                })
                .map { response -> response?.items }
                .subscribe(object : Observer<List<Route>?> {
                    override fun onNext(routes : List<Route>?) {
                        items = ArrayList(routes)
                        Log.d(TAG, "Size " + items?.size())
                        var i = 0
                        while(i < 10){
                            Log.d(TAG, "item " + items?.get(i)?.number)
                            i++
                        }
                        Log.d(TAG, "onNext")
                        publish()
                    }

                    override fun onCompleted() {
                        Log.d(TAG, "Completed")
                    }

                    override fun onError(e: Throwable?) {
                        Log.d(TAG, e?.getMessage())
                    }
                })
                /*
                .subscribe{ routes -> // TODO: Error handling with Object Expressions
                    // http://kotlinlang.org/docs/reference/object-declarations.html
                    // https://kmangutov.wordpress.com/2015/03/28/android-mvp-consuming-restful-apis/
                    items = ArrayList(routes)
                    publish()
                    Log.d(TAG, "Routes Loaded")
                }*/

    }

    fun takeView(view : RoutesFragment?) {
        Log.d(TAG, "takeView")
        routesView = view
        publish()
    }

    private fun publish() {
        Log.d(TAG, "publish")
        if(routesView != null) {
            if(items != null) {
                Log.d(TAG, "calling onContentLoaded")
                routesView!!.onContentLoaded(items!!)
            } else {
                Log.d(TAG, "items are null")
                if(error != null) {
                    routesView!!.onError(error!!, false)
                }
            }
        }
    }

}
