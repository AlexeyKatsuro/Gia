package com.katsiro.alexey.gia.data.repositories

import com.katsiro.alexey.gia.data.dao.CategoryPurchaseLinkDao
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.data.entities.Purchase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface LinkRepository {

    suspend fun addLink(categoryId: Long, purchase: Purchase): Long

    suspend fun addLinkWithReturn(categoryId: Long, purchase: Purchase): Purchase

    suspend fun getPurchasesByCategory(categoryId: Long): List<Purchase>

    suspend fun getCategoriesByPurchase(purchaseId: Long): List<Category>
}

class LinkRepositoryImpl(
    private val linkDao: CategoryPurchaseLinkDao
) : LinkRepository {
    override suspend fun addLink(categoryId: Long, purchase: Purchase): Long {
        return withContext(Dispatchers.IO) {
            linkDao.insertLink(categoryId, purchase)
        }
    }

    override suspend fun addLinkWithReturn(categoryId: Long, purchase: Purchase): Purchase {
        return withContext(Dispatchers.IO) {
            linkDao.insertWithReturn(categoryId, purchase)
        }
    }

    override suspend fun getPurchasesByCategory(categoryId: Long): List<Purchase> {
        return withContext(Dispatchers.IO) {
            linkDao.getPurchasesByCategory(categoryId)
        }
    }

    override suspend fun getCategoriesByPurchase(purchaseId: Long): List<Category> {
        return withContext(Dispatchers.IO) {
            linkDao.getCategoriesByPurchase(purchaseId)
        }
    }

}