package net.tuxv.miwaykotlin.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Stop
import java.util.ArrayList

// TODO: Combine StopsAdapter and RoutesAdapter into a common parent
class StopsAdapter(val context : Context) : BaseAdapter() {

    var items : ArrayList<Stop>? = null

    override fun getCount() = items?.size() ?: 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder : ViewHolder
        var returnView = convertView

        if(returnView == null) {
            returnView = LayoutInflater.from(context).inflate(R.layout.list_item_stop, parent, false)
            holder = ViewHolder(returnView?.findViewById(R.id.name) as TextView)

            returnView?.setTag(holder)
        } else {
            holder = returnView?.getTag() as ViewHolder
        }

        val stop = getItem(position)
        holder.title.setText(stop.name)

        return returnView
    }

    override fun getItem(position: Int): Stop {
        return items!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // TODO: Use a kotlin setter
    fun setData(data : ArrayList<Stop>) {
        items = data
        notifyDataSetChanged()
    }

    class ViewHolder(val title : TextView)
}
