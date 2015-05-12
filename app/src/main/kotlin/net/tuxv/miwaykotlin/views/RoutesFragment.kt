package net.tuxv.miwaykotlin.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.presenters.RoutesPresenter
import net.tuxv.mvplibrary.fragments.MvpLceFragment
import net.tuxv.mvplibrary.traits.Presenter

public class RoutesFragment : MvpLceFragment<Route>() {

    override fun getLayoutRes() = R.layout.fragment_routes

    override fun createPresenter() = RoutesPresenter()

}