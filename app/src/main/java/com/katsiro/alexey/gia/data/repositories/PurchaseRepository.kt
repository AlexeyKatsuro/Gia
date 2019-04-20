package com.katsiro.alexey.gia.data.repositories

import com.katsiro.alexey.gia.data.dao.PurchaseDao
import com.katsiro.alexey.gia.data.entities.Purchase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PurchaseRepository : LocalRepository<Purchase>

class PurchaseRepositoryImpl(
    private val purchaseDao: PurchaseDao
) : PurchaseRepository {


    override suspend fun getCount(): Int {
        return withContext(Dispatchers.IO) {
            purchaseDao.getCount()
        }
    }

    override suspend fun delete(data: Purchase) {
        return withContext(Dispatchers.IO) {
            purchaseDao.delete(data)
        }
    }

    override suspend fun getAll(): List<Purchase> {
        return withContext(Dispatchers.IO) {
            purchaseDao.getAllPurchases()
        }
    }

    override suspend fun insert(data: Purchase): Long {
        return withContext(Dispatchers.IO) {
            purchaseDao.insert(data)
        }
    }

    override suspend fun update(data: Purchase) {
        return withContext(Dispatchers.IO) {
            purchaseDao.update(data)
        }
    }

    override suspend fun get(id: Long): Purchase {
        return withContext(Dispatchers.IO) {
            purchaseDao.getPurchase(id)
        }
    }
}