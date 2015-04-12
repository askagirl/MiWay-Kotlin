package traits

import traits.mvp.MvpPresenter

/**
 * Created by yasith on 15-04-12.
 */

trait RoutesPresenter : MvpPresenter<RoutesView> {
    fun loadRoutes(pullToRefresh : Boolean)
}