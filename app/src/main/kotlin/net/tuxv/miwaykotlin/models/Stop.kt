package net.tuxv.miwaykotlin.models

import java.util.ArrayList

class Stop {
    var number : String? = null
    var index : Int? = null
    var name : String? = null

    class StopResponse {
        var items : List<Stop> = ArrayList()
    }
}
