package com.katsiro.alexey.gia.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: T) : Long

    @Update
    fun update(data: T)

    @Delete
    fun delete(data: T)
}