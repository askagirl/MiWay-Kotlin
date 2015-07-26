package net.tuxv.miwaykotlin.services

import net.tuxv.miwaykotlin.models.Favourite

/**
 * Created by yasith on 25/07/15.
 */

interface DatabaseService {
    abstract  fun getFavourites() :List<Favourite>
    abstract  fun saveFavourite(favourite : Favourite)
}
