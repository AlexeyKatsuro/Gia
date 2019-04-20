package com.katsiro.alexey.gia.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Category(
    var name: String,
    var parentId: Long
): Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}