package com.example.gitapp.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListenerLinear(
    private val mLinearLayoutManager: LinearLayoutManager
) :
    RecyclerView.OnScrollListener() {

    private var currentPage = 1
    private var visibleThreshold = 5
    private var lastVisibleItem = 0
    private var totalItemCount = 0
    private var isLoading: Boolean = false


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            totalItemCount = mLinearLayoutManager.itemCount
            lastVisibleItem =
                mLinearLayoutManager.findLastCompletelyVisibleItemPosition()
            if (!isLoading) {
                if (totalItemCount <= lastVisibleItem + visibleThreshold) {
                    isLoading = true
                    currentPage++
                    onLoadMore(currentPage)
                }
            }
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        onScrollStateChanged(newState)
    }

    abstract fun onLoadMore(current_page: Int)

    abstract fun onScrollStateChanged(newState: Int)
    fun newDataReceived() {
        isLoading = false
        currentPage = 1
        totalItemCount = 0
        lastVisibleItem = 0
    }

}