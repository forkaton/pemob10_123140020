package com.forkaton.pemob7_123140020

import android.app.Application
import com.forkaton.pemob7_123140020.di.dataModule
import com.forkaton.pemob7_123140020.di.viewModelModule
import com.forkaton.pemob7_123140020.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@MainApplication)
            // Masukkan ketiga modul secara berurutan
            modules(listOf(dataModule, viewModelModule, platformModule))
        }
    }
}