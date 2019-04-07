package com.katsiro.alexey.gia.data.dao

import androidx.room.*
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.data.entities.CategoryPurchaseLink
import com.katsiro.alexey.gia.data.entities.Purchase

@Dao
abstract class CategoryPurchaseLinkDao {

    @Transaction
    open fun insertLink(categoryId: Long, purchase: Purchase): Long {
        val addedPurchase = insertWithReturn(purchase)
        val link = CategoryPurchaseLink(categoryId, addedPurchase.id)
        return insert(link)
    }

    @Transaction
    open fun insertWithReturn(categoryId: Long, purchase: Purchase): Purchase {
        val addedPurchase = insertWithReturn(purchase)
        val link = CategoryPurchaseLink(categoryId, addedPurchase.id)
        insert(link)
        return addedPurchase
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPurchase(purchase: Purchase): Long

    @Transaction
    open fun insertWithReturn(purchase: Purchase): Purchase {
        return getPurchase(insertPurchase(purchase))
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(link: CategoryPurchaseLink): Long

    @Transaction
    open fun getPurchasesByCategory(categoryId: Long): List<Purchase> {
        return getLinkWithCategory(categoryId).map {
            getPurchase(it.purchaseId)
        }
    }

    @Transaction
    open fun getCategoriesByPurchase(purchaseId: Long): List<Category> {
        return getLinkWithPurchase(purchaseId).map {
            getCategory(it.categoryId)
        }
    }

    @Query("SELECT * FROM purchase WHERE id = :id")
    abstract fun getPurchase(id: Long): Purchase

    @Query("SELECT * FROM category WHERE id = :id")
    abstract fun getCategory(id: Long): Category

    @Query("SELECT*FROM categorypurchaselink WHERE categoryId = :categoryId")
    abstract fun getLinkWithCategory(categoryId: Long): List<CategoryPurchaseLink>

    @Query("SELECT*FROM categorypurchaselink WHERE purchaseId = :purchaseId")
    abstract fun getLinkWithPurchase(purchaseId: Long): List<CategoryPurchaseLink>

}