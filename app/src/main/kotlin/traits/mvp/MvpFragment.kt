package traits.mvp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

/**
 * Created by yasith on 15-04-12.
 */

abstract class MvpFragment<P : MvpPresenter<MvpView>> : Fragment(), MvpView {

    protected abstract var presenter : P

    abstract fun createPresenter() : P

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super<Fragment>.onViewCreated(view, savedInstanceState)

        if (presenter == null) {
            presenter = createPresenter()
            if (presenter == null) {
                throw NullPointerException("Presenter can't be null")
            }
        }
        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super<Fragment>.onDestroyView()
        presenter.detachView(getRetainInstance())
    }
}
