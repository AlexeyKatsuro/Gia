package com.katsiro.alexey.gia.data.dao

import androidx.room.*
import com.katsiro.alexey.gia.data.entities.Purchase
import java.util.*

@Dao
abstract class PurchaseDao {

    @Query("SELECT * FROM purchase")
    abstract fun getAllPurchases(): List<Purchase>

    @Query("SELECT * FROM purchase WHERE id = :id")
    abstract fun getPurchase(id: Long): Purchase

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(purchase: Purchase): Long

    @Insert
    abstract fun update(purchase: Purchase): Long

    @Query("SELECT * FROM purchase WHERE date BETWEEN :from AND :to")
    abstract fun findBetweenDates(from: Date, to: Date): List<Purchase>

    @Transaction
    open fun insertWithReplace(purchase: Purchase): Purchase {
        return getPurchase(insert(purchase))
    }
}