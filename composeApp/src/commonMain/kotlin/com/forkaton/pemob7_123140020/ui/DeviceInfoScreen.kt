package com.forkaton.pemob7_123140020.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.forkaton.pemob7_123140020.platform.DeviceInfo
import org.koin.compose.koinInject

@Composable
fun DeviceInfoScreen() {
    // Meminta Koin memberikan objek DeviceInfo.
    // PENTING: Baris ini akan error/merah di Android Studio saat ini, 
    // karena kita belum mengajari Koin cara memuatnya (kita lakukan di Latihan 2).
    val deviceInfo: DeviceInfo = koinInject() 
    
    Column {
        Text("Device: ${deviceInfo.getDeviceName()}")
        Text("OS: ${deviceInfo.getOsVersion()}")
        Text("App Version: ${deviceInfo.getAppVersion()}")
    }
}