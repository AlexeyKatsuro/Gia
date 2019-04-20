package com.katsiro.alexey.gia.ui.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GridSpacingItemDecoration(
    private val spacing: Int,
    private val spanCount: Int,
    @RecyclerView.Orientation private val orientation: Int,
    private val includeEdge: Boolean = false
) : RecyclerView.ItemDecoration() {

    constructor(
        context: Context,
        @DimenRes resDimen: Int,
        spanCount: Int,
        @RecyclerView.Orientation orientation: Int,
        includeEdge: Boolean = false
    ) : this(context.resources.getDimension(resDimen).toInt(), spanCount, orientation, includeEdge)

    constructor(
        context: Context,
        @DimenRes resDimen: Int,
        layoutManager: GridLayoutManager,
        includeEdge: Boolean = false
    ) : this(context, resDimen, layoutManager.spanCount, layoutManager.orientation, includeEdge)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        var column = 0
        var row = 0
        when (orientation) {
            RecyclerView.HORIZONTAL -> {
                column = position / spanCount
                row = position % spanCount
            }
            RecyclerView.VERTICAL -> {
                column = position % spanCount
                row = position / spanCount
            }
        }
        calculateOffsets(outRect, column, row)
    }

    private fun calculateOffsets(rect: Rect, column: Int, row: Int) {
        if (includeEdge) {
            rect.bottom = spacing
            if (row == 0) {
                rect.top = spacing
            }

            rect.right = spacing
            if (column == 0) {
                rect.left = spacing
            }
        } else {
            if (row != spanCount - 1) {
                rect.bottom = spacing
            }
            if (column != 0) {
                rect.left = spacing
            }
        }
    }
}