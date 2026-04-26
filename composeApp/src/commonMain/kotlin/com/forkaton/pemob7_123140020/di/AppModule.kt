package com.forkaton.pemob7_123140020.di

import com.forkaton.pemob7_123140020.local.DatabaseDriverFactory
import com.forkaton.pemob7_123140020.local.NoteRepository
import com.forkaton.pemob7_123140020.local.SettingsManager
import com.forkaton.pemob7_123140020.viewmodel.NotesViewModel
import com.forkaton.pemob7_123140020.platform.DeviceInfo
import com.forkaton.pemob7_123140020.platform.NetworkMonitor
import com.forkaton.pemob7_123140020.AppDatabase
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    // 1. Platform-Specific Features (Pertemuan 8)
    single { DeviceInfo() }
    single { NetworkMonitor(get()) } // Koin otomatis mencari Application Context di Android

    // 2. Database (Praktikum 7)
    single { DatabaseDriverFactory() }
    single { 
        // Membuat instance AppDatabase menggunakan driver dari DatabaseDriverFactory
        AppDatabase(get<DatabaseDriverFactory>().createDriver()) 
    }

    // 3. Repository & Manager (Praktikum 7)
    single { SettingsManager(Settings()) }
    single { NoteRepository(get()) } // get() akan otomatis mengambil DatabaseDriverFactory di sini berdasarkan implementasi lama, 
                                     // tapi karena kita sudah punya AppDatabase di atas, kita akan sesuaikan repository nanti jika perlu.
                                     // Namun berdasarkan kode NoteRepository yang dibaca, ia menerima DatabaseDriverFactory.

    // 4. ViewModels
    viewModelOf(::NotesViewModel)
}