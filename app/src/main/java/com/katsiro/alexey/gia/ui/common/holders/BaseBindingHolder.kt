package com.katsiro.alexey.gia.ui.common.holders

import android.view.View
import androidx.annotation.IdRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.katsiro.alexey.gia.BR

abstract class BaseBindingHolder<T, VB : ViewDataBinding>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract val bidning: VB
    open fun bind(index: Int, item: T) = defaultBind(item)

    open fun defaultBind(item: T, @IdRes variableId: Int = BR.item) {
        bidning.setVariable(variableId,item)
        bidning.executePendingBindings()
    }
}


