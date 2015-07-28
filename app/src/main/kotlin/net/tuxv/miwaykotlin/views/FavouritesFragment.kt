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
import net.tuxv.miwaykotlin.models.Favourite
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.presenters.FavouritesPresenter
import net.tuxv.miwaykotlin.utils.FavouritesAdapter
import net.tuxv.miwaykotlin.utils.JSON_ROUTE
import net.tuxv.miwaykotlin.utils.JSON_STOP
import org.lucasr.twowayview.TwoWayView
import java.util.ArrayList
import kotlin.properties.Delegates

public class FavouritesFragment : Fragment() {

    val TAG = "FavouritesFragment"

    var recyclerView : TwoWayView by Delegates.notNull()
    var adapter : FavouritesAdapter by Delegates.notNull()
    var presenter : FavouritesPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)

        Log.d(TAG, "onCreateView")

        recyclerView = view?.findViewById(R.id.list) as TwoWayView
        recyclerView.setAdapter(adapter)

        recyclerView.setOnItemClickListener { parent, child, position, id ->
            val fav = recyclerView.getAdapter().getItem(position) as Favourite

            Log.d(TAG, "Clicked on " + fav.routeNum  + fav.direction + fav.stopId)

            val route = Route()
            route.name = fav.routeName
            route.num = fav.routeNum
            route.direction = fav.direction

            val stop = Stop()
            stop.name = fav.stopName
            stop.stopId = fav.stopId
            stop.lon = fav.lon
            stop.lat = fav.lat

            val intent = Intent(getActivity(), javaClass<TimesActivity>())
            intent.putExtra(JSON_ROUTE, Gson().toJson(route))
            intent.putExtra(JSON_STOP, Gson().toJson(stop))

            startActivity(intent)
        }


        return view
    }

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        adapter = FavouritesAdapter(getActivity())

        if(presenter == null) {
            presenter = FavouritesPresenter()
            // TODO: Fix this
            presenter!!.initialize(this)
            presenter!!.attachView(this)
        } else {
            presenter!!.attachView(this)
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "onStart")
        presenter?.initialize(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.attachView(null)
        // TODO
    }

    fun onContentLoaded(favourites : ArrayList<Favourite>) {
        Log.d(TAG, "onContentLoaded")
        adapter.setData(favourites)
    }

    fun onError(error: Throwable, pullToRefresh : Boolean) {
        // TODO
    }
}