package com.forkaton.pemob7_123140020

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.forkaton.pemob7_123140020.local.DatabaseDriverFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Kita mengirimkan Konteks Android ke pabrik Database
            App(databaseDriverFactory = DatabaseDriverFactory(this))
        }
    }
}