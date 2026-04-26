package com.forkaton.pemob7_123140020.local

import app.cash.sqldelight.db.SqlDriver

// expect berarti implementasi aslinya akan ada di folder platform (androidMain & iosMain)
expect class DatabaseDriverFactory() {
    fun createDriver(): SqlDriver
}