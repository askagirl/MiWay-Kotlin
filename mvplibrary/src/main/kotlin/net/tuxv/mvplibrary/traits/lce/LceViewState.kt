package net.tuxv.mvplibrary.traits.lce

import net.tuxv.mvplibrary.traits.ViewState

/**
 * Created by yasith on 15-05-11.
 */

interface LceViewState <M> : ViewState {
    fun setStateShowContent(data : M)
    fun setStateShowError(e : Throwable, pullToRefresh : Boolean)
    fun setStateShowLoading(pullToRefresh : Boolean)
}
