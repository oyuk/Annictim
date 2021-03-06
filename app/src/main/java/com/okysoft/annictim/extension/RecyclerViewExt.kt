package com.okysoft.annictim.extension

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadMoreScrollListener(private val layoutManager: LinearLayoutManager,
                             private val listener: LoadMoreScrollListener.Listener
) : RecyclerView.OnScrollListener() {
    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (totalItemCount - visibleItemCount <= firstVisibleItem) {// + visibleThreshold)) {
            listener.onLoadMore(currentPage)
        }
    }

    interface Listener {
        fun onLoadMore(currentPage: Int)
    }

}

fun RecyclerView.addOnLoadMoreListener(layoutManager: LinearLayoutManager,
                                                                    listener: LoadMoreScrollListener.Listener) {
    addOnScrollListener(LoadMoreScrollListener(layoutManager, listener))
}