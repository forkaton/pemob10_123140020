package com.forkaton.pemob7_123140020.di

import org.koin.dsl.module
import com.forkaton.pemob7_123140020.platform.NetworkMonitor
import com.forkaton.pemob7_123140020.local.DatabaseDriverFactory
import com.forkaton.pemob7_123140020.platform.DeviceInfo
import com.forkaton.pemob7_123140020.AppDatabase
import org.koin.core.module.Module

actual val platformModule = module {
    single { NetworkMonitor() }
    single { DatabaseDriverFactory() }
    single { DeviceInfo() }
    single { AppDatabase(get<DatabaseDriverFactory>().createDriver()) }
}