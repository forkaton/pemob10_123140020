package com.forkaton.pemob7_123140020.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.forkaton.pemob7_123140020.platform.DeviceInfo
import org.koin.compose.koinInject

@Composable
fun DeviceInfoScreen() {
    val deviceInfo: DeviceInfo = koinInject()

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Device Information",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text("Device: ", fontWeight = FontWeight.SemiBold)
                Text(deviceInfo.getDeviceName())
            }
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text("OS: ", fontWeight = FontWeight.SemiBold)
                Text(deviceInfo.getOsVersion())
            }
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                Text("App Version: ", fontWeight = FontWeight.SemiBold)
                Text(deviceInfo.getAppVersion())
            }
        }
    }
}