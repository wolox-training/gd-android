package ar.com.wolox.android.example.utils

import androidx.recyclerview.widget.RecyclerView

abstract class DynamicScrollRV(val performAction: () -> Unit) : RecyclerView.OnScrollListener() {

    var currentScroll = 0

    private val TAG: String = DynamicScrollRV::class.java.simpleName

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_IDLE && currentScroll >= 0 && !isLoading() && hasMore()) performAction()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        currentScroll = dy
    }

    /**
     * Loading data
     */
    abstract fun isLoading(): Boolean

    /**
     * More items will be added to the RV when it's true.
     *
     */
    abstract fun hasMore(): Boolean
}