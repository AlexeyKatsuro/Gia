package com.katsiro.alexey.gia.ui.common.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * A generic ViewHolder that works with a [ViewDataBinding].
 * @param <T> The type of the ViewDataBinding.
 */

class BindingViewHolder<T : Any, out V : ViewDataBinding>(
    val binding: V,
    val variableId: Int
) : RecyclerView.ViewHolder(binding.root) {

    var item: T? = null
    var itemPosition: Int = -1

    fun bind(newItem: T, position: Int) {
        item = newItem
        itemPosition = position
        binding.setVariable(variableId, item)
        binding.executePendingBindings()
    }

    fun setOnItemClickListener(listener: (binding: V, position: Int, item: T) -> Unit) {
        binding.root.setOnClickListener {
            listener.invoke(binding, itemPosition, requireNotNull(item))
        }
    }
}