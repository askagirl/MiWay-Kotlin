package net.tuxv.miwaykotlin.views

import android.app.Activity
import android.content.Intent
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
import kotlinx.android.synthetic.activity_stops
import net.tuxv.miwaykotlin.utils.JSON_ROUTE
import net.tuxv.miwaykotlin.utils.JSON_STOP
import net.tuxv.miwaykotlin.utils.StopsAdapter

public class StopsActivity : Activity() {

    val TAG = "StopsActivity"

    var recyclerView : TwoWayView by Delegates.notNull()
    var adapter : StopsAdapter by Delegates.notNull()
    var presenter : StopsPresenter? = null

    override fun onCreate(savedInstanceState : Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)

        val jsonRoute = getIntent().getStringExtra(JSON_ROUTE)
        val route = Gson().fromJson(jsonRoute, javaClass<Route>())

        setContentView(R.layout.activity_stops)

        recyclerView = findViewById(R.id.list) as TwoWayView
        adapter = StopsAdapter(this)
        recyclerView.setAdapter(adapter)

        recyclerView.setOnItemClickListener { parent, child, position, id ->
            var stop = recyclerView.getAdapter().getItem(position) as Stop
            Log.d(TAG, "Clicked on " + stop.name)

            var intent = Intent(this, javaClass<TimesActivity>())

            intent.putExtra(JSON_ROUTE, jsonRoute)
            intent.putExtra(JSON_STOP, Gson().toJson(stop))
        }

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
        adapter.setData(items)
    }

    // TODO: Implement
    fun onError(error : Throwable, pullToRefresh : Boolean) {}
}