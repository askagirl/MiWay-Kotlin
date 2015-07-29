package net.tuxv.miwaykotlin.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.View
import android.widget.TextView
import com.crashlytics.android.Crashlytics
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.activity_main.pager
import kotlinx.android.synthetic.activity_main.pagerTabStrip
import net.tuxv.miwaykotlin.R

public class MainActivity : FragmentActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")
        super.onCreate(savedInstanceState)

        Fabric.with(this, Crashlytics())


        setContentView(R.layout.activity_main)

        pager.setAdapter(PagerAdapter(getSupportFragmentManager()))
        pagerTabStrip.setTabIndicatorColor(R.color.accent)

        val subTitle = findViewById(R.id.subtitle) as TextView
        subTitle.setVisibility(View.GONE)

        // Ads
        val adView = findViewById(R.id.adView) as AdView
        val adRequest = AdRequest.Builder().addTestDevice("79DDF7617C44C2C04C9DF163F5BC6144").build();
        adView.loadAd(adRequest)
    }

    class PagerAdapter : FragmentPagerAdapter {

        val titles = arrayOf("Favourites", "Routes")

        public constructor(fm : FragmentManager) : super(fm)

        override fun getCount() : Int = titles.size()
        override fun getPageTitle(position: Int): CharSequence? = titles.get(position)

        override fun getItem(position: Int): Fragment? = when (position) {
            0 -> FavouritesFragment()
            1 -> RoutesFragment()
            else -> FavouritesFragment()
        }
    }
}