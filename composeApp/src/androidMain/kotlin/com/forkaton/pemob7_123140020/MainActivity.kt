package com.forkaton.pemob7_123140020

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Cukup panggil fungsi App() tanpa parameter karena Koin sudah menangani DI
            App()
        }
    }
}