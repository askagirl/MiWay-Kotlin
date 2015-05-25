package net.tuxv.miwaykotlin.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import net.tuxv.miwaykotlin.R
import net.tuxv.miwaykotlin.models.Route
import net.tuxv.miwaykotlin.models.Stop
import net.tuxv.miwaykotlin.models.Time
import net.tuxv.miwaykotlin.presenters.NextTimesPresenter
import java.util.ArrayList
import kotlin.properties.Delegates

public class NextTimesFragment : Fragment() {
    val TAG = "NextTimesFragment"

    val time1 : TextView by bindView(R.id.time1)
    val time2 : TextView by bindView(R.id.time2)
    val time3 : TextView by bindView(R.id.time3)

    var route : Route by Delegates.notNull()
    var stop : Stop by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        return inflater?.inflate(R.layout.fragment_next_times, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val activity = getActivity() as TimesActivity

        route = activity.route
        stop = activity.stop

        // TODO: Make sure this survives config changes
        NextTimesPresenter(route, stop).attachView(this)
    }

    override fun onStart() {
        super.onStart()

        time1.setText("Times of ${route.name} at ${stop.name}")
    }

    fun onContentLoaded(times : ArrayList<Time>) {
        time1.setText(times.get(0).toString())
        time2.setText(times.get(1).toString())
        time3.setText(times.get(2).toString())
    }

    fun onError(error : Throwable, pullToRefresh : Boolean) {}
}




