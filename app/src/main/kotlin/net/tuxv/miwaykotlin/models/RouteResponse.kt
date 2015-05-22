package net.tuxv.miwaykotlin.models

import com.google.gson.annotations.Expose
import java.util.ArrayList

class RouteResponse {
    var items : List<Route> = ArrayList()
    var kind : String? = null
}
