package net.tuxv.miwaykotlin.presenters

import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.views.RoutesFragment
import net.tuxv.mvplibrary.fragments.MvpFragment
import net.tuxv.mvplibrary.presenters.BaseLcePresenter
import net.tuxv.mvplibrary.traits.Presenter
import net.tuxv.mvplibrary.traits.View
import java.util.ArrayList

/**
 * Created by yasith on 15-05-12.
 */

class RoutesPresenter : BaseLcePresenter<Route, RoutesFragment>() {
    override fun loadData() = Route("UniE", "N", 110)

}