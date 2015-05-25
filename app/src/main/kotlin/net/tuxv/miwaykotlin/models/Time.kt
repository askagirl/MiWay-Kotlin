package net.tuxv.miwaykotlin.models

import java.util.LinkedList

public class Time(val hour : Int, val minute : Int, val ampm : String) : Comparable<Time> {

    public var notes : LinkedList<String> = LinkedList()

    // TODO: Count ampm
    override fun compareTo(other: Time) = when{
        this.hour < other.hour -> -1
        this.hour == other.hour && this.minute < other.minute -> -1
        else -> 1
    }

    override fun toString() = "${hour}:${minute}:${ampm}"

    // TODO: 24 hour time
}
