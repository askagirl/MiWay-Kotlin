package net.tuxv.mvplibrary.traits.lce

import net.tuxv.mvplibrary.traits.View

/**
 * Created by yasith on 15-04-12.
 */

trait LceView<M> : View {
    fun showLoading(pullToRefresh : Boolean)
    fun showContent()
    fun showError(e : Throwable, pullToRefresh : Boolean)
    fun setData(data : M)
}