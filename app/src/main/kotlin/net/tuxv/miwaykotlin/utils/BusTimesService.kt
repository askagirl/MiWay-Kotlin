package net.tuxv.miwaykotlin.utils

import com.facebook.stetho.okhttp.StethoInterceptor
import com.squareup.okhttp.OkHttpClient
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.models.Time
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable
import kotlin.properties.Delegates

class BusTimesService() {
    val url = "http://gtfsapi-1003.appspot.com/api/v1/"
    public var busTimesApi : BusTimesApi by Delegates.notNull()

    init {

        val okHttpClient = OkHttpClient()
        okHttpClient.networkInterceptors().add(StethoInterceptor())

        val restAdapter = RestAdapter.Builder()
            .setEndpoint(url)
            .setClient(OkClient(okHttpClient))
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
