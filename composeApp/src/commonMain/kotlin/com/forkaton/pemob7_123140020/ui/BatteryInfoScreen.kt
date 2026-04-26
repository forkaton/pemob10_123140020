package com.forkaton.pemob7_123140020.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.forkaton.pemob7_123140020.platform.BatteryInfo
import org.koin.compose.koinInject

@Composable
fun BatteryInfoScreen() {
    val batteryInfo: BatteryInfo = koinInject()
    val level = batteryInfo.getBatteryLevel()
    val isCharging = batteryInfo.isCharging()

    Card(
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Battery Status",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (isCharging) Icons.Default.BatteryChargingFull else Icons.Default.BatteryFull,
                    contentDescription = "Battery Icon",
                    tint = when {
                        level <= 20 -> Color.Red
                        level <= 50 -> Color.Yellow
                        else -> Color.Green
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("$level%", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isCharging) "(Charging ⚡)" else "(Not Charging)",
                    color = if (isCharging) Color(0xFF4CAF50) else Color.Gray,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}