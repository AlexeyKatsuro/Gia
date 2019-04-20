package com.katsiro.alexey.gia.data.dao

import androidx.room.*
import com.katsiro.alexey.gia.data.entities.Category

@Dao
abstract class CategoryDao : BaseDao<Category>{

    @Query("SELECT count(*) FROM category")
    abstract fun getCount(): Int

    @Query("SELECT * FROM category")
    abstract fun getAllCategories(): List<Category>

    @Query("SELECT * FROM category WHERE id = :id")
    abstract fun getCategory(id: Long): Category

}