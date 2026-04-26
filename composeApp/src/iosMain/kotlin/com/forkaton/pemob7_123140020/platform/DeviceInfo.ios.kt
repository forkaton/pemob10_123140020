package com.forkaton.pemob7_123140020.platform

import platform.UIKit.UIDevice
import platform.Foundation.NSBundle

// 'actual' berarti ini adalah implementasi nyata untuk iOS
actual class DeviceInfo actual constructor() {
    actual fun getDeviceName(): String {
        // UIDevice adalah API bawaan iOS untuk membaca info perangkat keras
        return UIDevice.currentDevice.name
    }

    actual fun getOsVersion(): String {
        return "${UIDevice.currentDevice.systemName} ${UIDevice.currentDevice.systemVersion}"
    }

    actual fun getAppVersion(): String {
        // Membaca versi aplikasi dari kamus sistem iOS (NSBundle)
        return NSBundle.mainBundle.infoDictionary?.get("CFBundleShortVersionString") as? String ?: "1.0.0"
    }
}