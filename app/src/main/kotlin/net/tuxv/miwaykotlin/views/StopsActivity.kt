package net.tuxv.miwaykotlin.views

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.presenters.StopsPresenter
import org.lucasr.twowayview.TwoWayView
import java.util.ArrayList
import kotlin.properties.Delegates

val JSON_ROUTE : String = "JsonRoute"

public class StopsActivity : Activity() {

    val TAG = "StopsActivity"

    var recyclerView : TwoWayView by Delegates.notNull()
    // TODO : Implement StopsAdapter
    // var adapter : StopsAdapter by Delegates.notNull()
    var presenter : StopsPresenter? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stops)

        val message = getIntent().getStringExtra(JSON_ROUTE)
        val route = Gson().fromJson(message, javaClass<Route>())

        // TODO: Implement ViewStates and see if this should be in onCreate
        if (presenter == null) {
            presenter = StopsPresenter(route)
            presenter!!.attachView(this)
        } else {
            presenter!!.attachView(this)
        }
    }

    // TODO: Implement
    fun onContentLoaded(items : ArrayList<Stop>) {

    }

    // TODO: Implement
    fun onError(error : Throwable, pullToRefresh : Boolean) {}
}