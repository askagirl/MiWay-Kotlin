package net.tuxv.miwaykotlin.utils

import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.RouteResponse
import retrofit.RestAdapter
import retrofit.http.GET
import rx.Observable
import kotlin.properties.Delegates

/**
 * Created by yasith on 15-05-21.
 */

trait BusTimesApi {
    [GET("/routecollection")]
    public fun getRoutes() : Observable<RouteResponse>
}

class BusTimesService() {
    val url = "https://tactical-unison-651.appspot.com/_ah/api/busTimes/v1/"
    public var busTimesApi : BusTimesApi by Delegates.notNull()

    init {
        val restAdapter = RestAdapter.Builder()
            .setEndpoint(url)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build()

        busTimesApi = restAdapter.create(javaClass<BusTimesApi>())
    }
}
