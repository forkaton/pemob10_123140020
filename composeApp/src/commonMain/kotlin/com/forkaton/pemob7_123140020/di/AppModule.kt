package com.forkaton.pemob7_123140020.di

import com.forkaton.pemob7_123140020.platform.DeviceInfo
import com.forkaton.pemob7_123140020.platform.NetworkMonitor
import org.koin.dsl.module

// Modul utama tempat kita mendaftarkan semua dependency
val appModule = module {
    
    // Mendaftarkan DeviceInfo agar bisa diambil menggunakan koinInject()
    single { DeviceInfo() }

    // Tambahkan baris ini. get() di sini akan otomatis mencari 'Context' di Android
    single { NetworkMonitor(get()) } 

    // CATATAN UNTUK ANSEL: 
    // Berhubung kita bertahap, kita daftarkan DeviceInfo dulu.
    // Nanti setelah sukses berjalan, kita akan migrasikan DatabaseDriverFactory, 
    // NoteRepository, dan NotesViewModel (dari Praktikum 7) ke dalam blok ini.
}