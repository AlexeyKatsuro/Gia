package com.katsiro.alexey.gia.data.repositories

import com.katsiro.alexey.gia.data.dao.PurchaseDao
import com.katsiro.alexey.gia.data.entities.Purchase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PurchaseRepository {

    suspend fun getPurchase(id: Long): Purchase

    suspend fun getAllPurchases(): List<Purchase>

    suspend fun add(purchase: Purchase): Long

    suspend fun update(purchase: Purchase): Long

    suspend fun insertWithReturn(purchase: Purchase): Purchase
}

class PurchaseRepositoryImpl(
    private val purchaseDao: PurchaseDao
) : PurchaseRepository {

    override suspend fun insertWithReturn(purchase: Purchase): Purchase {
        return withContext(Dispatchers.IO) {
            purchaseDao.insertWithReplace(purchase)
        }
    }

    override suspend fun getAllPurchases(): List<Purchase> {
        return withContext(Dispatchers.IO) {
            purchaseDao.getAllPurchases()
        }
    }

    override suspend fun add(purchase: Purchase): Long {
        return withContext(Dispatchers.IO) {
            purchaseDao.insert(purchase)
        }
    }

    override suspend fun update(purchase: Purchase): Long {
        return withContext(Dispatchers.IO) {
            purchaseDao.update(purchase)
        }
    }

    override suspend fun getPurchase(id: Long): Purchase {
        return withContext(Dispatchers.IO) {
            purchaseDao.getPurchase(id)
        }
    }

}