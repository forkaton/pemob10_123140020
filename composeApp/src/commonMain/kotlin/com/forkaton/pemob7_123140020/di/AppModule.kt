package com.forkaton.pemob7_123140020.di

import com.forkaton.pemob7_123140020.local.NoteRepository
import com.forkaton.pemob7_123140020.local.SettingsManager
import com.forkaton.pemob7_123140020.viewmodel.NotesViewModel
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.koin.core.module.Module

// 1. Modul khusus untuk Data (Database, Repository, Preferences)
val dataModule = module {
    single { SettingsManager(Settings()) }
    single { NoteRepository(get()) }
}

// 2. Modul khusus untuk ViewModel
val viewModelModule = module {
    factoryOf(::NotesViewModel) 
}

// 3. Modul platform spesifik
expect val platformModule: Module