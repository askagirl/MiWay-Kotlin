package net.tuxv.miwaykotlin.models

import io.realm.RealmObject
import java.util.ArrayList

class Route : RealmObject() {
    var name : String? = null
    var direction : String? = null
    var num : String? = null

    class RouteResponse {
        var data : List<Route> = ArrayList()
    }
}

