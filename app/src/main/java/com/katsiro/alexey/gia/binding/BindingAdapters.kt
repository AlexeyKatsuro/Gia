package com.katsiro.alexey.gia.binding

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.katsiro.alexey.gia.ui.common.GridSpacingItemDecoration

/**
 * Data Binding adapters specific to the app.
 */

@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.isVisible = show
}

@BindingAdapter("visibleInvisible")
fun showHideInvisible(view: View, show: Boolean) {
    view.isInvisible = !show
}

@BindingAdapter("dividerItemDecoration")
fun RecyclerView.setDefaultItemDecoration(required: Boolean) {
    if (required) {
        val lm = (layoutManager as LinearLayoutManager)
        val dividerItemDecoration = DividerItemDecoration(context, lm.orientation)
        addItemDecoration(dividerItemDecoration)
    }
}

@BindingAdapter(value = ["gridItemDecoration", "includeEdge"], requireAll = false)
fun RecyclerView.setGridItemDecoration(spacing: Float, includeEdge: Boolean?) {
    val lm = layoutManager as GridLayoutManager
    addItemDecoration(
        GridSpacingItemDecoration(
            spacing.toInt(),
            lm.spanCount,
            lm.orientation,
            includeEdge ?: false
        )
    )
}

