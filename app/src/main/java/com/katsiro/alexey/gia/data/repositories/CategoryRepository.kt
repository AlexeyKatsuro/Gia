package com.katsiro.alexey.gia.data.repositories

import com.katsiro.alexey.gia.data.dao.CategoryDao
import com.katsiro.alexey.gia.data.entities.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CategoryRepository {

    suspend fun getCategory(id: Long): Category

    suspend fun getAllCategories(): List<Category>

    suspend fun add(category: Category): Long

    suspend fun update(category: Category): Long

    suspend fun insertWithReturn(category: Category): Category
}

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao
) : CategoryRepository {

    override suspend fun insertWithReturn(category: Category): Category {
        return withContext(Dispatchers.IO) {
            categoryDao.insertWithReplace(category)
        }
    }

    override suspend fun getAllCategories(): List<Category> {
        return withContext(Dispatchers.IO) {
            categoryDao.getAllCategories()
        }
    }

    override suspend fun add(category: Category): Long {
        return withContext(Dispatchers.IO) {
            categoryDao.insert(category)
        }
    }

    override suspend fun update(category: Category): Long {
        return withContext(Dispatchers.IO) {
            categoryDao.update(category)
        }
    }

    override suspend fun getCategory(id: Long): Category {
        return withContext(Dispatchers.IO) {
            categoryDao.getCategory(id)
        }
    }
}