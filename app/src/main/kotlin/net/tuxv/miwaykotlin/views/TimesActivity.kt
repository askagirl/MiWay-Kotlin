package net.tuxv.miwaykotlin.views

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.utils.JSON_ROUTE
import net.tuxv.miwaykotlin.utils.JSON_STOP
import kotlin.properties.Delegates

public class TimesActivity : FragmentActivity() {

    val TAG = "TimesActivity"

    var route : Route by Delegates.notNull()
    var stop : Stop by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)

        val jsonRoute = getIntent().getStringExtra(JSON_ROUTE)
        val jsonStop = getIntent().getStringExtra(JSON_STOP)

        val gson = Gson()
        this.route = gson.fromJson(jsonRoute, javaClass<Route>())
        this.stop = gson.fromJson(jsonStop, javaClass<Stop>())

        setContentView(R.layout.activity_times)

        val title = findViewById(R.id.title) as TextView
        title.setText("${route.name}")

        val subTitle = findViewById(R.id.subtitle) as TextView
        subTitle.setText("${route.direction}")
    }
}
