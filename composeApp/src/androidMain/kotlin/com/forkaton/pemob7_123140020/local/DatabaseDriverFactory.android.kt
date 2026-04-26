package com.forkaton.pemob7_123140020.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.forkaton.pemob7_123140020.AppDatabase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class DatabaseDriverFactory actual constructor() : KoinComponent {
    
    private val context: Context by inject()

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = context,
            name = "notes.db"
        )
    }
}