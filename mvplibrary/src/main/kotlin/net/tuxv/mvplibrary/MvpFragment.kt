package net.tuxv.mvplibrary

import android.support.v4.app.Fragment
import net.tuxv.mvplibrary.traits.Presenter
import net.tuxv.mvplibrary.traits.View
import kotlin.properties.Delegates

/**
 * Created by yasith on 15-05-12.
 */

abstract class MvpFragment : Fragment(), View{

    var presenter = createPresenter()

    abstract fun createPresenter() : Presenter

    abstract fun getLayoutRes() : Int
}
