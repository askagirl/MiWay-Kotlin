package net.tuxv.miwaykotlin.services

import net.tuxv.miwaykotlin.models.Favourite
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop

/**
 * Created by yasith on 25/07/15.
 */

public class RealmDatabaseService : DatabaseService {

    override fun saveFavourite(route: Route, stop: Stop) {
        throw UnsupportedOperationException()
    }

    override fun isFavourite(route: Route, stop: Stop): Boolean {
        throw UnsupportedOperationException()
    }

    override fun flipFavourite(route: Route, stop: Stop) {
        throw UnsupportedOperationException()
    }

    override fun getFavourites(): List<Favourite> {
        throw UnsupportedOperationException()
    }

}
