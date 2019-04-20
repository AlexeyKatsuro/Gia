package com.katsiro.alexey.gia.utils.extensions

import androidx.annotation.LayoutRes
import com.afollestad.recyclical.ItemDefinition
import com.afollestad.recyclical.RecyclicalSetup
import com.afollestad.recyclical.ViewHolderCreator
import com.afollestad.recyclical.itemdefinition.RealItemDefinition
import com.katsiro.alexey.gia.ui.common.holders.BaseBindingHolder

inline fun <reified IT : Any> RecyclicalSetup.withData(
    @LayoutRes layoutRes: Int,
    noinline block: ItemDefinition<IT>.() -> Unit
): ItemDefinition<IT> {
    return RealItemDefinition(this, IT::class.java)
        .apply(block)
        .also { definition ->
            registerItemDefinition(
                className = definition.itemClassName,
                viewType = layoutRes,
                definition = definition
            )
        }
}


fun <IT : Any, VH : BaseBindingHolder<IT, *>> ItemDefinition<IT>.onBindingBind(
    viewHolderCreator: ViewHolderCreator<VH>
): ItemDefinition<IT> {
    return onBind(viewHolderCreator) { index, item -> bind(index, item) }
}
