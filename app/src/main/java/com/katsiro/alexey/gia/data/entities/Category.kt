package com.katsiro.alexey.gia.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    val name: String,
    val parentId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}