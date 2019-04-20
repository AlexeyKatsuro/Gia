package com.katsiro.alexey.gia.ui.common.holders

import android.view.View
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.databinding.ItemCategoryBinding

class CategoryHolder(view: View) : BaseBindingHolder<Category, ItemCategoryBinding>(view) {

    override val bidning = ItemCategoryBinding.bind(view)

}