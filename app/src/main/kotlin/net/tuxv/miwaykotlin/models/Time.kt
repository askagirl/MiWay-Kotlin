package net.tuxv.miwaykotlin.models

import java.util.*
import java.lang.String;


public class Time: Comparable<Time> {

    var hour : Int? = null
    var minute : Int? = null

    // TODO: Count ampm
    override fun compareTo(other: Time) = when{
        this.hour!! < other.hour!! -> -1
        this.hour!! == other.hour!! && this.minute!! < other.minute!! -> -1
        else -> 1
    }

    override fun toString() = "${hour}:${String.format("%02d", minute)}"

    // TODO: 24 hour time

    class TimeResponse {
        var data : List<Time> = ArrayList()
    }

}
