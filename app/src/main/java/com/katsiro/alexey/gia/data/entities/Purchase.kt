package com.katsiro.alexey.gia.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Purchase(
    val cost: Float,
    val date: Date,
    val note: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}