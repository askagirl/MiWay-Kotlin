package models

/**
 * Created by yasith on 15-04-12.
 */

data class Route(val name : String, val direction : String, val number : Int) {
    fun id() = number as String + direction
}