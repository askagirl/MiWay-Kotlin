package net.tuxv.mvplibrary.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import icepick.Icepick

/**
 * Created by yasith on 15-05-12.
 */

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstaceState : Bundle) {
        super.onCreate(savedInstaceState)
        Icepick.restoreInstanceState(this, savedInstaceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Icepick.saveInstanceState(this, outState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val layoutRes = getLayoutRes()

        val view = inflater.inflate(layoutRes, container, false)
        return view
    }

    abstract fun getLayoutRes() : Int

}