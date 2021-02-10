package com.outliers.cleancodekt.framework

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewPaginator(val recyclerView: RecyclerView, val parent: RecyclerPaginatorParent,
    val batchSize: Int, val startPage: Int) : RecyclerView.OnScrollListener() {

    interface RecyclerPaginatorParent {
        val isLoading: Boolean
        val isLastPage: Boolean
        fun loadMore(page: Int, batchSize: Int)
    }

    var layoutManager: RecyclerView.LayoutManager? = recyclerView.layoutManager
    val threshold: Int = 3
    var pageNum: Int = startPage
    var isRequestSent = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if(newState == RecyclerView.SCROLL_STATE_IDLE){
            var totalItems: Int = 0
            var visibleItems: Int = 0
            var lastItemPos: Int = 0
            if (layoutManager is LinearLayoutManager){
                val linearLayoutManager = (layoutManager as LinearLayoutManager)
                totalItems = linearLayoutManager.itemCount
                visibleItems = linearLayoutManager.childCount
                lastItemPos = linearLayoutManager.findLastVisibleItemPosition()
            } else if(layoutManager is GridLayoutManager){
                val gridLayoutManager = (layoutManager as LinearLayoutManager)
                totalItems = gridLayoutManager.itemCount
                visibleItems = gridLayoutManager.childCount
                lastItemPos = gridLayoutManager.findLastVisibleItemPosition()
            }

            if(parent.isLoading or parent.isLastPage)
                return

            if(visibleItems+lastItemPos+threshold > totalItems){
                if(!parent.isLastPage and !isRequestSent){
                    parent.loadMore(++pageNum, batchSize)
                    isRequestSent = true
                }else{
                    isRequestSent = false
                }
            }
        }
    }
}