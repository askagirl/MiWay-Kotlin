package net.tuxv.miwaykotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.tuxv.miwaykotlin.models.Favourite
import net.tuxv.miwaykotlin.presenters.FavouritesPresenter
import org.lucasr.twowayview.TwoWayView
import java.util.ArrayList
import kotlin.properties.Delegates

public class FavouritesFragment : Fragment() {

    val TAG = "FavouritesFragment"

    var recyclerView : TwoWayView by Delegates.notNull()
    var presenter : FavouritesPresenter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_favourites, container, false)

        Log.d(TAG, "onCreateView")
        recyclerView = view?.findViewById(R.id.list) as TwoWayView

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(presenter == null) {
            presenter = FavouritesPresenter()
        } else {
            presenter!!.takeView(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.takeView(null)
        // TODO: Maybe call a different function
    }

    fun onContentLoaded(favourites : ArrayList<Favourite>) {
        // TODO: Implement the adapter
    }

    fun onError(error : Throwable, pullToRefresh : Boolean) {
        // TODO: Implement the loading
    }
}
