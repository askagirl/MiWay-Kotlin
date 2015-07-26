package net.tuxv.miwaykotlin.utils

import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.models.Time
import retrofit.RestAdapter
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable
import kotlin.properties.Delegates

class BusTimesService() {
    val url = "http://gtfsapi-1003.appspot.com/api/v1/"
    public var busTimesApi : BusTimesApi by Delegates.notNull()

    init {
        val restAdapter = RestAdapter.Builder()
            .setEndpoint(url)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build()

        busTimesApi = restAdapter.create(javaClass<BusTimesApi>())
    }

    interface BusTimesApi {
        @GET("/routes")
        public fun getRoutes() : Observable<Route.RouteResponse>

        @GET("/stops/{routeId}/{direction}")
        public fun getStops(@Path("routeId") routeId : String,
                            @Path("direction") direction : String) : Observable<Stop.StopResponse>

        @GET("/times/{routeId}/{direction}/{stopId}")
        public fun getTimes(@Path("routeId") routeId : String,
                            @Path("direction") direction : String,
                            @Path("stopId") stopId : String) : Observable<Time.TimeResponse>
    }
}
