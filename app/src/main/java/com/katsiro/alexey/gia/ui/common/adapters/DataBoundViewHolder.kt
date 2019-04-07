package com.katsiro.alexey.gia.ui.common.adapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.katsiro.alexey.gia.BR
import com.katsiro.alexey.gia.utils.extensions.executeAfter

/**
 * A generic ViewHolder that works with a [ViewDataBinding].
 * @param <T> The type of the ViewDataBinding.
 */
class DataBoundViewHolder<T, out V : ViewDataBinding>(val binding: V) :
    RecyclerView.ViewHolder(binding.root) {

    var item: T? = null
    var itemPosition: Int = -1

    private var itemClickListener: ((binding: V, position: Int, item: T) -> Unit)? = null

    init {
        binding.root.setOnClickListener {
            if (itemPosition >= 0 && item != null) {
                itemClickListener?.invoke(binding, itemPosition, item!!)
            }
        }
    }

    fun bind(newItem: T, position: Int) {
        item = newItem
        itemPosition = position
        binding.executeAfter {
            setVariable(BR.item, item)
        }

    }

    fun setOnItemClickListener(listener: ((binding: V, position: Int, item: T) -> Unit)?) {
        itemClickListener = listener
    }
}
