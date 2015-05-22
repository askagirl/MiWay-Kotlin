package net.tuxv.miwaykotlin.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.presenters.RoutesPresenter
import net.tuxv.miwaykotlin.utils.RoutesAdapter
import org.lucasr.twowayview.TwoWayView
import java.util.ArrayList
import kotlin.properties.Delegates

public class RoutesFragment : Fragment() {

    val TAG = "RoutesFragment"

    var recyclerView: TwoWayView by Delegates.notNull()
    var adapter : RoutesAdapter by Delegates.notNull()
    var presenter : RoutesPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_routes, container, false)

        Log.d(TAG, "onCreateView")
        recyclerView = view?.findViewById(R.id.list) as TwoWayView
        adapter = RoutesAdapter(getActivity())
        recyclerView.setAdapter(adapter)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(presenter == null) {
            presenter = RoutesPresenter()
            presenter!!.attachView(this)
        } else {
            presenter!!.attachView(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.attachView(null)
        // TODO
    }

    fun onContentLoaded(routes : ArrayList<Route>) {
        Log.d(TAG, "onContentLoaded")
        adapter.setData(routes)
    }

    fun onError(error: Throwable, pullToRefresh : Boolean) {
        // TODO
    }
}