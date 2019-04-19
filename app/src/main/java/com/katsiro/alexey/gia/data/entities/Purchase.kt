package com.katsiro.alexey.gia.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class Purchase(
    val cost: Float,
    val date: Date,
    val note: String = "",
    val categoryId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    val formatedDate : String
    get() {
        val format = SimpleDateFormat("dd-MMM-yyyy hh:mm", Locale.getDefault())
        return format.format(date)
    }
}