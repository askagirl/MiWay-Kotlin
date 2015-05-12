package net.tuxv.mvplibrary.presenters

import net.tuxv.mvplibrary.traits.Presenter
import net.tuxv.mvplibrary.traits.View
import java.lang.ref.WeakReference
import kotlin.properties.Delegates

/**
 * Created by yasith on 15-05-12.
 */

abstract class BasePresenter<V : View> : Presenter<V> {

    var viewRef : WeakReference<V>? = null

    override fun attachView(view: V) {
        viewRef = WeakReference<V>(view);
    }

    override fun detachView(retainInstance: Boolean) {
        viewRef?.clear()
        viewRef = null
    }

    /**
     * Always call isViewAttached before calling getView()
     */
    fun getView() = viewRef?.get()

    /**
     * Check if a not null view is attached
     */
    fun isViewAttached() = (viewRef?.get() ?: null) != null
}
