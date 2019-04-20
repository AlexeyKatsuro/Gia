package com.katsiro.alexey.gia.data.repositories

import com.katsiro.alexey.gia.data.dao.CategoryDao
import com.katsiro.alexey.gia.data.entities.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface CategoryRepository: LocalRepository<Category>

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao
) : CategoryRepository {


    override suspend fun delete(data: Category) {
       return withContext(Dispatchers.IO){

       }
    }

    override suspend fun getCount(): Int {
        return withContext(Dispatchers.IO){
            categoryDao.getCount()
        }
    }

    override suspend fun getAll(): List<Category> {
        return withContext(Dispatchers.IO) {
            categoryDao.getAllCategories()
        }
    }

    override suspend fun insert(data: Category): Long {
        return withContext(Dispatchers.IO) {
            categoryDao.insert(data)
        }
    }

    override suspend fun update(data: Category) {
        return withContext(Dispatchers.IO) {
            categoryDao.update(data)
        }
    }

    override suspend fun get(id: Long): Category {
        return withContext(Dispatchers.IO) {
            categoryDao.getCategory(id)
        }
    }
}