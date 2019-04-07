package com.katsiro.alexey.gia.di

import androidx.room.Room
import com.katsiro.alexey.gia.data.GiaDatabase
import com.katsiro.alexey.gia.data.repositories.CategoryRepository
import com.katsiro.alexey.gia.data.repositories.CategoryRepositoryImpl
import com.katsiro.alexey.gia.data.repositories.PurchaseRepository
import com.katsiro.alexey.gia.data.repositories.PurchaseRepositoryImpl
import com.katsiro.alexey.gia.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel(get()) }

    single<PurchaseRepository> { PurchaseRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }


    single { get<GiaDatabase>().purchaseDao() }
    single { get<GiaDatabase>().categoryDao() }
    single { get<GiaDatabase>().categoryPurchaseLinkDao() }

    single {
        Room.databaseBuilder(
            androidApplication(),
            GiaDatabase::class.java,
            GiaDatabase::class.java.simpleName
        )
            .build()
    }
}