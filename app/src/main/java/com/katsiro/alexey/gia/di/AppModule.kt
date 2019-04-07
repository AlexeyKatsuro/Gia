package com.katsiro.alexey.gia.di

import androidx.room.Room
import com.katsiro.alexey.gia.data.GiaDatabase
import com.katsiro.alexey.gia.data.repositories.*
import com.katsiro.alexey.gia.ui.CategoryViewModel
import com.katsiro.alexey.gia.ui.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel(get(), get()) }
    viewModel { CategoryViewModel(get()) }

    single<LinkRepository> { LinkRepositoryImpl(get()) }
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