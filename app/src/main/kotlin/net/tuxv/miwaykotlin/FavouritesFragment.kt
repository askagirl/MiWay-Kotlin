package net.tuxv.miwaykotlin

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.lucasr.twowayview.TwoWayView
import kotlinx.android.synthetic.fragment_favourites.*
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.presenters.FavouritesPresenter
import java.util.ArrayList
import kotlin.properties.Delegates

public class FavouritesFragment : Fragment() {

    var recyclerView : TwoWayView by Delegates.notNull()
    var presenter : FavouritesPresenter by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_favourites, container, false)

        recyclerView =  view.findViewById(R.id.recyclerView) as TwoWayView

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(presenter == null) {
            presenter = FavouritesPresenter()
        } else {
            presenter.takeView(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.takeView(null)
        // TODO: Maybe call a different function
    }

    fun onContentLoaded(routes : ArrayList<Route>) {
        // TODO: Implement the adapter
    }

    fun onError(error : Throwable, pullToRefresh : Boolean) {
        // TODO: Implement the loading
    }
}

