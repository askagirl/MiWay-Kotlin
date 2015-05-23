package net.tuxv.miwaykotlin.views

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.utils.JSON_ROUTE
import net.tuxv.miwaykotlin.utils.JSON_STOP

public class TimesActivity : Activity() {

    val TAG = "TimesActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)

        val jsonRoute = getIntent().getStringExtra(JSON_ROUTE)
        val jsonStop = getIntent().getStringExtra(JSON_STOP)

        val gson = Gson()
        val route = gson.fromJson(jsonRoute, javaClass<Route>())
        val stop = gson.fromJson(jsonStop, javaClass<Stop>())

    }
}
