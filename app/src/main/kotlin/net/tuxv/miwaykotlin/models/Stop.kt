package net.tuxv.miwaykotlin.models

import io.realm.RealmObject
import java.util.ArrayList

class Stop : RealmObject() {
    var stopId : String? = null
    var name : String? = null
    var lat : Float? = null
    var lon : Float? = null

    class StopResponse {
        var data : List<Stop> = ArrayList()
    }
}
