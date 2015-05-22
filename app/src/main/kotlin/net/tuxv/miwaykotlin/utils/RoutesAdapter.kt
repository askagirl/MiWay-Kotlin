package net.tuxv.miwaykotlin.utils

import android.content.Context
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

/**
 * Created by yasith on 15-05-21.
 */

class RoutesAdapter(val context: Context) : BaseAdapter() {
    val tag = "RoutesAdapter"
    init {
        Log.d(tag, "Routes Adapter initialized")
    }

    var items : ArrayList<Route>? = null

    private val TAG = "RoutesAdapter"

    override fun getCount(): Int {
        Log.d(TAG, "getCount " + (items?.size() ?:0 ))
        return items?.size() ?: 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder : ViewHolder
        var returnView = convertView

        if(returnView == null) {
            returnView = LayoutInflater.from(context).inflate(R.layout.list_item_route, parent, false)

            holder = ViewHolder(returnView?.findViewById(R.id.name) as TextView)

            returnView?.setTag(holder)
        } else {
            holder = returnView?.getTag() as ViewHolder
        }

        val route = getItem(position)
        holder.title.setText(route.name)

        return returnView
    }

    override fun getItem(position: Int): Route {
        return items!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        // TODO: !
        return position.toLong()
    }

    fun setData(data : ArrayList<Route>) {
        Log.d(TAG, "setData")
        items = data
        notifyDataSetChanged()
    }

    class ViewHolder(val title : TextView)
}