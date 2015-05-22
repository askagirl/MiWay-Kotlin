package net.tuxv.miwaykotlin

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.activity_main.*;

public class MainActivity : FragmentActivity() {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate")

        pager.setAdapter(PagerAdapter(getSupportFragmentManager()))
        pagerTabStrip.setTabIndicatorColor(R.color.accent)
    }

    class PagerAdapter : FragmentPagerAdapter {

        val titles = array("Favourites", "Routes")

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
