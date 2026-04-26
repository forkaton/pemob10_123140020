package com.forkaton.pemob7_123140020.di

import com.forkaton.pemob7_123140020.local.NoteRepository
import com.forkaton.pemob7_123140020.local.SettingsManager
import com.forkaton.pemob7_123140020.viewmodel.NotesViewModel
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.koin.core.module.Module

// Modul untuk logic yang sama di semua platform
val commonModule = module {
    single { SettingsManager(Settings()) }
    single { NoteRepository(get()) }
    
    // Ganti viewModelOf menjadi factoryOf
    factoryOf(::NotesViewModel)
}

// Deklarasi bahwa setiap platform akan punya modulnya sendiri
expect val platformModule: Module