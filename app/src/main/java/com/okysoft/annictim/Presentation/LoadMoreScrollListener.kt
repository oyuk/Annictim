package com.okysoft.annictim.Presentation

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class LoadMoreScrollListener(private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
    private var current_page = 1

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (totalItemCount - visibleItemCount <= firstVisibleItem) {// + visibleThreshold)) {
            current_page++
//            onLoadMore(current_page)
        }
    }

//    abstract fun onLoadMore(current_page: Int)
}