package net.tuxv.miwaykotlin.utils

/**
 * Created by yasith on 25/07/15.
 */

fun shortDirection(direction : String?) = when(direction!!) {
    "Southbound" -> "S"
    "Northbound" -> "N"
    "Eastbound" -> "E"
    "Westbound" -> "W"
    "Counterclockwise" -> "CCW"
    "Clockwise" -> "CW"
    else -> ""
}