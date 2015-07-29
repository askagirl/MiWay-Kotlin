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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
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

    var colorNormal : Int by Delegates.notNull()
    var colorSelected : Int by Delegates.notNull()

    var heart : Drawable by Delegates.notNull()

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

        databaseService = CupboardDatabaseService.getInstance(this)

        heart = getResources().getDrawable(R.drawable.ic_action_favorite)
        colorNormal = getResources().getColor(R.color.white)
        colorSelected = getResources().getColor(R.color.accent)


        // Ads
        val adView = findViewById(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().addTestDevice("79DDF7617C44C2C04C9DF163F5BC6144").build();
        adView.loadAd(adRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "onCreateOptionsMenu")
        getMenuInflater().inflate(R.menu.menu_times, menu)

        val favouriteItem = menu?.findItem(R.id.favourite)
        favouriteItem?.setIcon(heart)

        updateIcon()

        favouriteItem?.setOnMenuItemClickListener({ item ->
            if (item.getItemId() == favouriteItem?.getItemId()) {
                Log.d(TAG, "Menu Item Clicked")

                databaseService.flipFavourite(route, stop)
                updateIcon()

                true
            }
            false
        })

        return true
    }

    private fun updateIcon(){

        if(databaseService.isFavourite(route, stop)) {
            heart.setColorFilter(PorterDuffColorFilter(colorSelected,
                    PorterDuff.Mode.MULTIPLY))
            Log.d(TAG, "Setting yellow icon")
        } else {
            Log.d(TAG, "Setting white icon")
            heart.setColorFilter(PorterDuffColorFilter(colorNormal,
                    PorterDuff.Mode.MULTIPLY))
        }
    }
}
