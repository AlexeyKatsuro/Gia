package com.katsiro.alexey.gia.ui.common.adapters

import androidx.recyclerview.widget.DiffUtil

class DiffItemCallback<T>(private val comparable: ((T) -> Any)? = null) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return comparable?.let { it(oldItem) == it(newItem) } ?: oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}
