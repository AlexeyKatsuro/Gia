package com.katsiro.alexey.gia.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryPurchaseLink(
    val categoryId: Long,
    val purchaseId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}