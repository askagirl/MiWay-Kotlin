package net.tuxv.miwaykotlin.models

import java.util.ArrayList

class Route {
    var id : String? = null
    var name : String? = null
    var direction : String? = null
    var number : String? = null

    class RouteResponse {
        var items : List<Route> = ArrayList()
    }
}

