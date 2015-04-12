package traits

import traits.mvp.MvpView

/**
 * Created by yasith on 15-04-12.
 */

trait LceView<T> : MvpView{
    fun showLoading(pullToRefresh : Boolean)
    fun showContent()
    fun showError(e : Throwable, pullToRefresh : Boolean)
    fun setData(data : T)
}