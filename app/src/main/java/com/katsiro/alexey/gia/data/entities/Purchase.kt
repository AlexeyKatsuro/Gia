package com.katsiro.alexey.gia.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Entity
@Parcelize
data class Purchase(
    val cost: Float,
    val date: Date,
    val note: String = "",
    val categoryId: Long
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @IgnoredOnParcel
    val formattedDate : String
    get() = SimpleDateFormat("hh:mm dd.MMM.yyyy ", Locale.getDefault()).format(date)

}