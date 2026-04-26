package com.forkaton.pemob7_123140020.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.forkaton.pemob7_123140020.AppDatabase

actual class DatabaseDriverFactory actual constructor() {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = AppDatabase.Schema,
            name = "notes.db"
        )
    }
}