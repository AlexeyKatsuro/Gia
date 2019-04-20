package com.katsiro.alexey.gia.ui.common.holders

import android.view.View
import com.afollestad.recyclical.ViewHolder
import com.katsiro.alexey.gia.databinding.ItemTextBinding
import com.katsiro.alexey.gia.utils.extensions.executeAfter

class TextViewHolder(itemView: View) : ViewHolder(itemView) {

    val bidning: ItemTextBinding = ItemTextBinding.bind(itemView)

    fun bind(item: String) {
        bidning.executeAfter {
            this.item = item
        }
    }
}
