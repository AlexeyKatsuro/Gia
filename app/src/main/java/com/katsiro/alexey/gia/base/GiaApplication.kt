package com.katsiro.alexey.gia.base

import android.app.Application
import com.katsiro.alexey.gia.BuildConfig
import com.katsiro.alexey.gia.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class GiaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@GiaApplication)
            modules(appModule)
        }
    }
}