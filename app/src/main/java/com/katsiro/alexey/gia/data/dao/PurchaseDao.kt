package com.katsiro.alexey.gia.data.dao

import androidx.room.*
import com.katsiro.alexey.gia.data.entities.Purchase
import java.util.*

@Dao
abstract class PurchaseDao: BaseDao<Purchase> {

    @Query("SELECT count(*) FROM purchase")
    abstract fun getCount(): Int

    @Query("SELECT * FROM purchase")
    abstract fun getAllPurchases(): List<Purchase>

    @Query("SELECT * FROM purchase WHERE id = :id")
    abstract fun getPurchase(id: Long): Purchase

    @Query("SELECT * FROM purchase WHERE date BETWEEN :from AND :to")
    abstract fun findBetweenDates(from: Date, to: Date): List<Purchase>

}