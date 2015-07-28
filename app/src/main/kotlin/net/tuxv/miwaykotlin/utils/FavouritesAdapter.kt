package net.tuxv.miwaykotlin.utils

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Favourite
import java.util.ArrayList

/**
 * Created by yasith on 27/07/15.
 */

class FavouritesAdapter(val context : Context) : BaseAdapter() {
    var items : ArrayList<Favourite>? = null

    // TODO: Refactor oneline functions
    override fun getCount(): Int {
        return items?.size() ?: 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder : ViewHolder
        var returnView = convertView

        if(returnView == null) {
            returnView = LayoutInflater.from(context).inflate(R.layout.list_item_favourite, parent, false)

            val routeName = returnView?.findViewById(R.id.route_name) as TextView
            val routeId = returnView?.findViewById(R.id.route_id) as TextView
            val stopName = returnView?.findViewById(R.id.stop_name) as TextView
            val badge = returnView?.findViewById(R.id.badge) as ViewGroup

            holder = ViewHolder(routeName, routeId, stopName, badge)

            returnView?.setTag(holder)

        } else {
            holder = returnView?.getTag() as ViewHolder
        }

        val fav = getItem(position)

        holder.routeName.setText(fav.routeName)
        holder.routeId.setText(fav.routeNum + " " + shortDirection(fav.direction!!))
        holder.badge.setBackground(createBadge(fav.routeNum!!.toInt()))
        holder.stopName.setText(fav.stopName)

        return returnView
    }

    override fun getItem(position : Int) : Favourite {
        return items!!.get(position)
    }

    override fun getItemId(position : Int) : Long {
        return position.toLong()
    }


    class ViewHolder(val routeName : TextView,
                     val routeId : TextView,
                     val stopName : TextView,
                     val badge : ViewGroup)

    fun setData(data: ArrayList<Favourite>) {
        items = data
        notifyDataSetChanged()
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
}