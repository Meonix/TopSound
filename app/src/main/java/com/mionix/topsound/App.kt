package com.mionix.topsound

import android.app.Application
import com.mionix.topsound.di.appModule
import com.mionix.topsound.di.viewModelModule
import com.mionix.topsound.di.repositoryModule
import com.mionix.topsound.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            androidLogger(Level.DEBUG)
            // Android context
            androidContext(this@App)
            // modules
            modules(arrayListOf(appModule,retrofitModule, viewModelModule,repositoryModule))
        }
    }
}