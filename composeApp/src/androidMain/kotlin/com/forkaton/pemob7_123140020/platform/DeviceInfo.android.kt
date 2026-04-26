package com.forkaton.pemob7_123140020.platform

import android.os.Build

// 'actual' berarti ini adalah implementasi nyata untuk Android
actual class DeviceInfo actual constructor() {
    actual fun getDeviceName(): String {
        // Mengambil pabrikan (misal: Samsung) dan modelnya (misal: SM-G998B)
        return "${Build.MANUFACTURER} ${Build.MODEL}"
    }

    actual fun getOsVersion(): String {
        // Mengambil versi Android (misal: Android 14) dan level API-nya
        return "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
    }

    actual fun getAppVersion(): String {
        return "1.0.0" // Nilai versi bawaan sementara
    }
}