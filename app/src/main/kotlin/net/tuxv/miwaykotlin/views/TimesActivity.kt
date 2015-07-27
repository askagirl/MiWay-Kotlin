package net.tuxv.miwaykotlin.views

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.widget.TextView
import com.google.gson.Gson
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.services.CupboardDatabaseService
import net.tuxv.miwaykotlin.services.DatabaseService
import net.tuxv.miwaykotlin.utils.JSON_ROUTE
import net.tuxv.miwaykotlin.utils.JSON_STOP
import kotlin.properties.Delegates

public class TimesActivity : AppCompatActivity() {

    val TAG = "TimesActivity"

    var yellowHeart : Drawable by Delegates.notNull()
    var whiteHeart : Drawable by Delegates.notNull()

    var route : Route by Delegates.notNull()
    var stop : Stop by Delegates.notNull()

    var databaseService : DatabaseService by Delegates.notNull()

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

        val toolbar = findViewById(R.id.toolbar) as Toolbar;
        setSupportActionBar(toolbar);

        whiteHeart = getResources().getDrawable(R.drawable.ic_action_favorite)
        yellowHeart = getResources().getDrawable(R.drawable.ic_action_favorite)
        yellowHeart.setColorFilter(PorterDuffColorFilter(getResources().getColor(R.color.accent),
                PorterDuff.Mode.MULTIPLY))

        databaseService = CupboardDatabaseService.getInstance(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_times, menu)

        val favouriteItem = menu?.findItem(R.id.favourite)
        favouriteItem?.setOnMenuItemClickListener({ item ->
            if (item.getItemId() == favouriteItem?.getItemId()) {
                Log.d(TAG, "Menu Item Clicked")

                if(databaseService.flipFavourite(route, stop)) {
                    favouriteItem?.setIcon(yellowHeart)
                } else {
                    favouriteItem?.setIcon(whiteHeart)
                }

                true
            }
            false
        })

        return true
    }
}
