package net.tuxv.miwaykotlin.models

import com.google.gson.annotations.Expose

/**
 * Created by yasith on 15-05-20.
 */

class Route {
    [Expose]
    var id : String? = null
    [Expose]
    var name : String? = null
    [Expose]
    var direction : String? = null
    [Expose]
    var number : String? = null
    [Expose]
    var kind : String? = null
}

