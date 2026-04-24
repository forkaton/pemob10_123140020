package com.forkaton.pemob7_123140020

import androidx.compose.ui.window.ComposeUIViewController
import com.forkaton.pemob7_123140020.local.DatabaseDriverFactory

fun MainViewController() = ComposeUIViewController {
    App(databaseDriverFactory = DatabaseDriverFactory())
}