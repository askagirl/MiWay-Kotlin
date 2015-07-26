package net.tuxv.miwaykotlin.services

import net.tuxv.miwaykotlin.models.Favourite

/**
 * Created by yasith on 25/07/15.
 */

public class RealmDatabaseService : DatabaseService {
    override fun getFavourites(): List<Favourite> {
        throw UnsupportedOperationException()
    }

    override fun saveFavourite(favourite: Favourite) {
        throw UnsupportedOperationException()
    }

}
