package net.tuxv.miwaykotlin.services

import net.tuxv.miwaykotlin.models.Favourite
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop

/**
 * Created by yasith on 25/07/15.
 */

interface DatabaseService {
    fun getFavourites() :List<Favourite>
    fun saveFavourite(route : Route, stop : Stop)
    fun isFavourite(route : Route, stop : Stop) : Boolean
    fun flipFavourite(route : Route, stop : Stop) : Boolean
}
