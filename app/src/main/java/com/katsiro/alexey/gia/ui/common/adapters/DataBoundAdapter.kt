package com.katsiro.alexey.gia.ui.common.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.katsiro.alexey.gia.BR
import kotlin.reflect.KProperty1

/**
 * A generic RecyclerView adapter that uses Data Binding & DiffUtil.
 *
 * @param <T> Type of the items in the list
 * @param <V> The type of the ViewDataBinding
 *
 * @param layoutRes Id of the layout for inflating the ViewDataBinding object
 * @param comparable Unique identification properties for DiffUtil comparison
 */
open class DataBoundAdapter<T : Any, V : ViewDataBinding>(
    @LayoutRes private val layoutId: Int,
    private val comparable: ((T) -> Any)? = null,
    @IdRes private val variableId: Int = BR.item
) : ListAdapter<T, BindingViewHolder<T, V>>(DiffItemCallback(comparable)) {

    protected open var itemClickListener: ((binding: V, position: Int, item: T) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<T, V> {
        val binding = createBinding(parent)
        return BindingViewHolder<T, V>(binding, variableId).apply {
            itemClickListener?.let { setOnItemClickListener(it) }
        }
    }

    protected open fun createBinding(parent: ViewGroup): V {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
    }

    override fun onBindViewHolder(holder: BindingViewHolder<T, V>, position: Int) {
        holder.bind(getItem(position), position)
    }

    open fun setOnItemClickListener(listener: (binding: V, position: Int, item: T) -> Unit) {
        itemClickListener = listener
    }
}