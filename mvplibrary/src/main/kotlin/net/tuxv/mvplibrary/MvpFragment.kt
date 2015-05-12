package net.tuxv.mvplibrary

import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import net.tuxv.mvplibrary.traits.lce.LceView
import net.tuxv.mvplibrary.R

/**
 * Created by yasith on 15-05-11.
 */

abstract class MvpLceFragment<M> : Fragment(), LceView<M> {
    override fun showLoading(pullToRefresh: Boolean) {
        if (!pullToRefresh) {
            animateLoadingViewIn();
        }
    }

    private abstract var loadingView : View
    private abstract var contentView : View
    private abstract var errorView : View

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)

        if (view != null) {
            loadingView = view.findViewById(R.id.loading);
            contentView = view.findViewById(R.id.content);
            errorView = view.findViewById(R.id.error);
        }
    }

    private fun animateLoadingViewIn() {
        // TODO
    }

    override fun showContent() {
        animateContentViewIn();
    }

    private fun animateContentViewIn() {
        // TODO
    }

    override fun showError(e: Throwable, pullToRefresh: Boolean) {
        throw UnsupportedOperationException()
    }

    override fun setData(data: M) {
        throw UnsupportedOperationException()
    }
}
