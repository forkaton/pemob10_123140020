package com.forkaton.pemob7_123140020

import android.app.Application
import com.forkaton.pemob7_123140020.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Memulai Koin khusus untuk menangani Context Android
        startKoin {
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}