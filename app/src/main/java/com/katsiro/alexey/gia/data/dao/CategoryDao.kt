package com.katsiro.alexey.gia.data.dao

import androidx.room.*
import com.katsiro.alexey.gia.data.entities.Category

@Dao
abstract class CategoryDao {

    @Query("SELECT * FROM category")
    abstract fun getAllCategories(): List<Category>

    @Query("SELECT * FROM category WHERE id = :id")
    abstract fun getCategory(id: Long): Category

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(category: Category): Long

    @Insert
    abstract fun update(category: Category): Long

    @Transaction
    open fun insertWithReplace(category: Category): Category {
        return getCategory(insert(category))
    }
}