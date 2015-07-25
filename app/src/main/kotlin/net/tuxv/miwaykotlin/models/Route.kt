package net.tuxv.miwaykotlin.models

import java.util.ArrayList

class Route {
    var name : String? = null
    var direction : String? = null
    var num : String? = null

    class RouteResponse {
        var data : List<Route> = ArrayList()
    }
}

