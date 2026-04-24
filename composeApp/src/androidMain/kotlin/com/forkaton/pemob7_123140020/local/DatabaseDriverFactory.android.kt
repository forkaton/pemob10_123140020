package com.forkaton.pemob7_123140020.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
// Import AppDatabase yang benar:
import com.forkaton.pemob7_123140020.AppDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = AppDatabase.Schema, // Ubah dari NotesDatabase menjadi AppDatabase
            context = context,
            name = "notes.db"
        )
    }
}