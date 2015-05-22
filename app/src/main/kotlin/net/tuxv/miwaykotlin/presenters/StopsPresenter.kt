package net.tuxv.miwaykotlin.presenters

import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.views.StopsActivity
import java.util.ArrayList

class StopsPresenter(val route : Route) {
    val TAG = "StopsPresenter"

    var items : ArrayList<Stop>? = null
    var error : Throwable? = null
    var view : StopsActivity? = null

    // TODO: Implement
    init {

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
