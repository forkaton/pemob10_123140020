package com.forkaton.pemob7_123140020

import android.app.Application
import com.forkaton.pemob7_123140020.di.commonModule
import com.forkaton.pemob7_123140020.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(commonModule, platformModule))
        }
    }
}