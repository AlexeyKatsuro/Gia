package com.katsiro.alexey.gia.utils.extensions

import androidx.annotation.LayoutRes
import com.afollestad.recyclical.ItemDefinition
import com.afollestad.recyclical.RecyclicalSetup
import com.afollestad.recyclical.itemdefinition.RealItemDefinition

inline fun <reified IT : Any> RecyclicalSetup.withHolder(
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
