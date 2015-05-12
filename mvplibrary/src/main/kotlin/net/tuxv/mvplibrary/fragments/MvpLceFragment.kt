package net.tuxv.mvplibrary.fragments

import android.os.Bundle
import android.view.View
import net.tuxv.mvplibrary.R
import net.tuxv.mvplibrary.traits.lce.LceView
import kotlin.properties.Delegates

/**
 * Created by yasith on 15-05-11.
 */

abstract class MvpLceFragment<M> : MvpFragment(), LceView<M> {
    override fun showLoading(pullToRefresh: Boolean) {
        if (!pullToRefresh) {
            animateLoadingViewIn();
        }
    }

    private var loadingView : View by Delegates.notNull()
    private var contentView : View by Delegates.notNull()
    private var errorView : View by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super<MvpFragment>.onViewCreated(view, savedInstanceState)

        loadingView = view.findViewById(R.id.loading);
        contentView = view.findViewById(R.id.content);
        errorView = view.findViewById(R.id.error);
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