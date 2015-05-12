package net.tuxv.miwaykotlin.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.mvplibrary.MvpLceFragment

public class RoutesFragment : MvpLceFragment<Route>() {

    override fun getLayoutRes(): Int {
        return R.layout.fragment_routes
    }
}