package com.katsiro.alexey.gia.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.katsiro.alexey.gia.R
import com.katsiro.alexey.gia.data.GiaDatabase
import com.katsiro.alexey.gia.data.entities.Category
import com.katsiro.alexey.gia.data.ioThread
import com.katsiro.alexey.gia.data.repositories.CategoryRepository
import com.katsiro.alexey.gia.data.repositories.CategoryRepositoryImpl
import com.katsiro.alexey.gia.data.repositories.PurchaseRepository
import com.katsiro.alexey.gia.data.repositories.PurchaseRepositoryImpl
import com.katsiro.alexey.gia.ui.CategoryViewModel
import com.katsiro.alexey.gia.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel(get(), get()) }
    viewModel { CategoryViewModel(get()) }

    single<PurchaseRepository> { PurchaseRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }


    single { get<GiaDatabase>().purchaseDao() }
    single { get<GiaDatabase>().categoryDao() }

    single {
        Room.databaseBuilder(
            androidApplication(),
            GiaDatabase::class.java,
            GiaDatabase::class.java.simpleName
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                ioThread {
                    val dao = get<GiaDatabase>().categoryDao()
                    val context = get<Context>()
                    dao.insert(Category(context.getString(R.string.category_store_all), -1))
                }
            }
        }).build()
    }
}