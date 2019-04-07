package com.katsiro.alexey.gia.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.katsiro.alexey.gia.data.dao.CategoryDao
import com.katsiro.alexey.gia.data.dao.CategoryPurchaseLinkDao
import com.katsiro.alexey.gia.data.dao.PurchaseDao
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.data.entities.CategoryPurchaseLink
import com.katsiro.alexey.gia.data.entities.Purchase


@Database(
    entities = [
        Purchase::class,
        Category::class,
        CategoryPurchaseLink::class],
    version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GiaDatabase : RoomDatabase() {

    abstract fun purchaseDao(): PurchaseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun categoryPurchaseLinkDao(): CategoryPurchaseLinkDao

}