package net.tuxv.miwaykotlin.presenters

import net.tuxv.miwaykotlin.models.Favourite
import net.tuxv.miwaykotlin.services.CupboardDatabaseService
import net.tuxv.miwaykotlin.views.FavouritesFragment
import net.tuxv.miwaykotlin.views.RoutesFragment
import java.util.ArrayList
import kotlin.properties.Delegates

// TODO: Implementation
class FavouritesPresenter() {

    var items : ArrayList<Favourite> by Delegates.notNull()
    var error : Throwable? = null
    var view : FavouritesFragment by Delegates.notNull()

    fun initialize(view : FavouritesFragment) {
        // Fix this, use dagger to get the database service
        this.view = view!!
        val databaseService = CupboardDatabaseService.getInstance(view.getActivity()!!)
        items = ArrayList(databaseService.getFavourites())
        publish()
    }

    fun attachView(view : FavouritesFragment?)  {
        this.view = view!!
        publish()
    }

    private fun publish() {
        if(view != null && items != null) {
            view.onContentLoaded(items!!)
        } else if(view != null && error != null){
            // TODO: work on pullToRefresh
            view.onError(error!!, false)
        }
    }
}