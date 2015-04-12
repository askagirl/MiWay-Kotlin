package traits.mvp

/**
 * Created by yasith on 15-04-12.
 *
 * MVP Based on Mosby
 */

trait MvpPresenter<V : MvpView> {
    /**
     * Attach the view to this Presenter
     */
    fun attachView(view : V)

    /**
     * Called when the view is destroyed. Invoked from Activity.detachView() or Fragment.onDestroyView()
     */
    fun detachView(retainInstance : Boolean)
}
