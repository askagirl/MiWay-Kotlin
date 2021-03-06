package net.tuxv.miwaykotlin.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.rahatarmanahmed.cpv.CircularProgressView
import com.google.gson.Gson
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.presenters.RoutesPresenter
import net.tuxv.miwaykotlin.utils.JSON_ROUTE
import net.tuxv.miwaykotlin.utils.RoutesAdapter
import org.lucasr.twowayview.TwoWayView
import java.util.ArrayList
import kotlin.properties.Delegates

public class RoutesFragment : Fragment() {

    val TAG = "RoutesFragment"

    var loading : CircularProgressView by Delegates.notNull()

    var recyclerView: TwoWayView by Delegates.notNull()
    var adapter : RoutesAdapter by Delegates.notNull()
    var presenter : RoutesPresenter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_routes, container, false)

        Log.d(TAG, "onCreateView")
        recyclerView = view?.findViewById(R.id.list) as TwoWayView
        adapter = RoutesAdapter(getActivity())
        recyclerView.setAdapter(adapter)

        recyclerView.setOnItemClickListener { parent, child, position, id ->
            var route = recyclerView.getAdapter().getItem(position) as Route

            Log.d(TAG, "Clicked on " + route.num + route.direction)

            var intent = Intent(getActivity(), javaClass<StopsActivity>())

            intent.putExtra(JSON_ROUTE, Gson().toJson(route));
            startActivity(intent)
        }

        loading = view?.findViewById(R.id.loading) as CircularProgressView

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
        loading.setVisibility(View.GONE)
        adapter.setData(routes)
    }

    fun onError(error: Throwable, pullToRefresh : Boolean) {
        // TODO
    }
}