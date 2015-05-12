package net.tuxv.mvplibrary.fragments

import android.support.v4.app.Fragment
import net.tuxv.mvplibrary.traits.Presenter
import net.tuxv.mvplibrary.traits.View

/**
 * Created by yasith on 15-05-12.
 */

abstract class MvpFragment : BaseFragment(), View {

    var presenter = createPresenter()

    abstract fun createPresenter() : Presenter<MvpFragment>
}