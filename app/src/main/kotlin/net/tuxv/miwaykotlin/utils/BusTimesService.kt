package net.tuxv.miwaykotlin.utils

import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import retrofit.RestAdapter
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable
import kotlin.properties.Delegates

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

    trait BusTimesApi {
        [GET("/routecollection")]
        public fun getRoutes() : Observable<Route.RouteResponse>

        [GET("/stopcollection/{routeId}")]
        public fun getStops([Path("routeId")] routeId : String) : Observable<Stop.StopResponse>
    }
}
