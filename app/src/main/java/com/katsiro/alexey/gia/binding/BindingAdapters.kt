/*
 * Copyright 2018 LWO LLC
 */

package by.lwo.trafficpolice.binding

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

@BindingAdapter("defaultItemDecoration")
fun RecyclerView.setDefaultItemDecoration(required: Boolean) {
    if (required) {
        val lm = (layoutManager as LinearLayoutManager)
        val dividerItemDecoration = DividerItemDecoration(context, lm.orientation)
        addItemDecoration(dividerItemDecoration)
    }
}

