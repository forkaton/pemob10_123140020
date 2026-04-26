package com.forkaton.pemob7_123140020.di

import org.koin.dsl.module
import com.forkaton.pemob7_123140020.platform.DeviceInfo
import com.forkaton.pemob7_123140020.platform.NetworkMonitor
import com.forkaton.pemob7_123140020.platform.BatteryInfo
import com.forkaton.pemob7_123140020.local.DatabaseDriverFactory
import com.forkaton.pemob7_123140020.AppDatabase

actual val platformModule = module {
    single { DatabaseDriverFactory() }
    single { NetworkMonitor() }
    single { DeviceInfo() }
    single { BatteryInfo() }
    single { AppDatabase(get<DatabaseDriverFactory>().createDriver()) }
}