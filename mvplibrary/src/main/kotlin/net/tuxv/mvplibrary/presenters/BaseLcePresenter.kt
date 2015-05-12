package net.tuxv.mvplibrary.presenters

import net.tuxv.mvplibrary.traits.lce.LceView

/**
 * Created by yasith on 15-05-12.
 */

abstract class BaseLcePresenter<M, V : LceView<M>> : BasePresenter<V>() {
    abstract fun loadData() : M
}
