package com.katsiro.alexey.gia.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: T): Long

    @Insert
    fun update(data: T): Long

    @Delete
    fun delete(data: T): Int
}