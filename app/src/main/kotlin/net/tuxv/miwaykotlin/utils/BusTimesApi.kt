package net.tuxv.miwaykotlin.utils

import net.tuxv.miwaykotlin.models.RouteResponse
import retrofit.http.GET
import rx.Observable

trait BusTimesApi {
    [GET("/routecollection")]
    public fun getRoutes() : Observable<RouteResponse>
}
