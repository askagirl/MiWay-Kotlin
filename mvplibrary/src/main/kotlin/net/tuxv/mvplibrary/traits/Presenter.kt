package net.tuxv.mvplibrary.traits

/**
 * Created by yasith on 15-04-12.
 *
 * MVP Based on Mosby
 */

trait Presenter<V : View> {
    /**
     * Attach the view to this Presenter
     */
    fun attachView(view : V)

    /**
     * Called when the view is destroyed. Invoked from Activity.detachView() or Fragment.onDestroyView()
     */
    fun detachView(retainInstance : Boolean)
}