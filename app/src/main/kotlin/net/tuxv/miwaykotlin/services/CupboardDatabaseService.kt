package net.tuxv.miwaykotlin.services

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import net.tuxv.miwaykotlin.models.Favourite
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import nl.qbusict.cupboard.CupboardFactory
import java.util.*

/**
 * Created by yasith on 25/07/15.
 */

public class CupboardDatabaseService(context : Context, name : String, version : Int) : SQLiteOpenHelper(context, name, null, version), DatabaseService {

    val TAG = "CupboardDatabaseService"

    companion object {
        val TAG = "CupboardDatabaseService.Companion"

        // Database properties
        val name = "MiwayApp"
        val version = 1

        fun getInstance(context : Context) : CupboardDatabaseService {
            CupboardFactory.cupboard().register(javaClass<Favourite>())

            val service = CupboardDatabaseService(context, name, version)

            Log.d(TAG, "Database created")
            return service
        }
    }

    // SQLiteOpenHelper overrides
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        CupboardFactory.cupboard().withDatabase(db).upgradeTables()
    }

    override fun onCreate(db: SQLiteDatabase) {
        CupboardFactory.cupboard().withDatabase(db).createTables()
    }

    // DatabaseService overrides
    override fun saveFavourite(route: Route, stop: Stop) {
        val fav = Favourite.withRouteStop(route, stop)

        val db = this.getWritableDatabase()
        CupboardFactory.cupboard().withDatabase(db).put(fav)
    }

    override fun isFavourite(route: Route, stop: Stop) = getFavourite(route, stop) != null

    override fun flipFavourite(route: Route, stop: Stop) {
        if(isFavourite(route, stop)) {
            val fav = getFavourite(route, stop)
            val db = this.getWritableDatabase()
            CupboardFactory.cupboard().withDatabase(db).delete(fav)
        } else {
            saveFavourite(route, stop)
        }
    }

    private fun getFavourite(route: Route, stop: Stop) : Favourite? {
        for(fav in getFavourites()) {
            if (fav.routeNum!!.equals(route.num) &&
                    fav.direction!!.equals(route.direction) &&
                    fav.stopId!!.equals(stop.stopId)) {
                return fav
            }
        }

        return null
    }

    override fun getFavourites(): List<Favourite> {
        Log.d(TAG, "getFavourites")
        val db = this.getReadableDatabase()
        val cursor = CupboardFactory.cupboard().withDatabase(db).query(javaClass<Favourite>()).getCursor()

        val favList = ArrayList<Favourite>()

        try {
            val itr = CupboardFactory.cupboard().withCursor(cursor).iterate(javaClass<Favourite>());
            for (fav in itr) {
                favList.add(fav)
                Log.d(TAG, fav.routeName + fav.direction + " " + fav.stopName)
            }

        } finally {
            cursor.close();
        }

        return favList
    }

}
