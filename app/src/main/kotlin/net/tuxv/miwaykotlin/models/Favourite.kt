package net.tuxv.miwaykotlin.models


class Favourite {

    companion object {
        fun withRouteStop(route : Route, stop : Stop) : Favourite {
            val fav = Favourite()

            fav.routeName = route.name
            fav.routeNum  = route.num
            fav.direction = route.direction

            fav.stopName  = stop.name
            fav.stopId    = stop.stopId
            fav.lat       = stop.lat
            fav.lon       = stop.lon

            return fav
        }
    }

    // For Cupboard
    var _id : Long? = null

    // For Route
    var routeName : String? = null
    var routeNum : String? = null
    var direction : String? = null

    // For Stop
    var stopName : String? = null
    var stopId : String? = null
    var lon : Float? = null
    var lat : Float? = null
}
