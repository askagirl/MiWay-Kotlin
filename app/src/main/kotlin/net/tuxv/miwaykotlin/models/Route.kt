package net.tuxv.miwaykotlin.models

/**
 * Created by yasith on 15-05-20.
 */

data class Route(val name : String, val direction : String, val number : Int){
    fun id() = number as String + direction
}
