package net.tuxv.miwaykotlin.models

import com.google.gson.annotations.Expose
import java.util.ArrayList

/**
 * Created by yasith on 15-05-22.
 */

class RouteResponse {
    [Expose]
    var items : List<Route> = ArrayList()

    [Expose]
    var kind : String? = null

    [Expose]
    var etag : String? = null
}
