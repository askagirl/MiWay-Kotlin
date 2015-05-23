package net.tuxv.miwaykotlin.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import java.util.ArrayList
import kotlin.properties.Delegates

// TODO: Modify this to support full routes
class RoutesAdapter(val context: Context) : BaseAdapter() {

    var items : ArrayList<Route>? = null

    // TODO: Refactor oneline functions
    override fun getCount(): Int {
        return items?.size() ?: 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder : ViewHolder
        var returnView = convertView

        if(returnView == null) {
            returnView = LayoutInflater.from(context).inflate(R.layout.list_item_route, parent, false)

            val name = returnView?.findViewById(R.id.name) as TextView
            val routeId = returnView?.findViewById(R.id.route_id) as TextView
            val badge = returnView?.findViewById(R.id.badge) as ViewGroup

            holder = ViewHolder(name, routeId, badge)

            returnView?.setTag(holder)
        } else {
            holder = returnView?.getTag() as ViewHolder
        }

        val route = getItem(position)

        holder.name.setText(route.name)
        holder.routeId.setText(route.number + shortDirection(route.direction!!))
        holder.badge.setBackground(createBadge(route.number!!.toInt()))

        return returnView
    }

    override fun getItem(position: Int): Route {
        return items!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // TODO: Use a kotlin setter
    fun setData(data : ArrayList<Route>) {
        items = data
        notifyDataSetChanged()
    }

    private fun shortDirection(direction : String) = when(direction) {
        "South" -> "S"
        "North" -> "N"
        "East" -> "E"
        "West" -> "W"
        "Counterclockwise" -> "CCW"
        "Clockwise" -> "CW"
        else -> ""
    }

    private fun createBadge(number : Int) : Drawable {
        // TODO: Investigate what to use instead of getDrawable
        val badge = context.getResources().getDrawable(R.drawable.route_badge)
        badge.setColorFilter(PorterDuffColorFilter(getColor(number), PorterDuff.Mode.MULTIPLY))

        return badge
    }

    private fun getColor(number : Int) = when(number) {
        in 100..199 -> context.getResources().getColor(R.color.miway_blue)
        in 300..400 -> context.getResources().getColor(R.color.school_bus_yellow)
        else -> context.getResources().getColor(R.color.miway_orange)
    }

    class ViewHolder(val name : TextView, val routeId : TextView, val badge : ViewGroup)
}