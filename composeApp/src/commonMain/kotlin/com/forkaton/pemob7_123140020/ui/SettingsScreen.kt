package com.forkaton.pemob7_123140020.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.BatteryStd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.forkaton.pemob7_123140020.local.SettingsManager
import com.forkaton.pemob7_123140020.platform.DeviceInfo
import com.forkaton.pemob7_123140020.platform.BatteryInfo
import org.koin.compose.koinInject
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settingsManager: SettingsManager,
    onNavigateBack: () -> Unit
) {
    val deviceInfo: DeviceInfo = koinInject()
    val batteryInfo: BatteryInfo = koinInject()
    val coroutineScope = rememberCoroutineScope()

    // Membaca status pengaturan dari SettingsManager
    val currentTheme by settingsManager.themeFlow.collectAsState(initial = "Navy")
    val currentSortOrder by settingsManager.sortOrderFlow.collectAsState(initial = "Newest")

    // 🌟 Data baterai hanya diambil 1x saat layar dibuka menggunakan remember
    val batteryLevel = remember { batteryInfo.getBatteryLevel() }
    val isCharging = remember { batteryInfo.isCharging() }

    val themes = listOf("Light", "Dark", "Navy")
    val sortOrders = listOf("Newest", "Oldest", "A-Z", "Z-A")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pengaturan") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- BAGIAN 1: PENGATURAN TEMA ---
            Column {
                Text(
                    text = "Tema Aplikasi",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                themes.forEach { themeOption ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { coroutineScope.launch { settingsManager.setTheme(themeOption) } }
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = currentTheme == themeOption,
                            onClick = { coroutineScope.launch { settingsManager.setTheme(themeOption) } }
                        )
                        Text(text = if (themeOption == "Navy") "Navy Ocean (Eksklusif)" else themeOption)
                    }
                }
            }

            HorizontalDivider()

            // --- BAGIAN 2: PENGATURAN URUTAN ---
            Column {
                Text(
                    text = "Urutkan Catatan",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                sortOrders.forEach { sortOption ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { coroutineScope.launch { settingsManager.setSortOrder(sortOption) } }
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = currentSortOrder == sortOption,
                            onClick = { coroutineScope.launch { settingsManager.setSortOrder(sortOption) } }
                        )
                        Text(text = sortOption)
                    }
                }
            }

            HorizontalDivider()

            // 1. Device Info Card
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Device Information",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                        Text("Device: ", fontWeight = FontWeight.Bold)
                        Text(deviceInfo.getDeviceName())
                    }
                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                        Text("OS: ", fontWeight = FontWeight.Bold)
                        Text(deviceInfo.getOsVersion())
                    }
                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                        Text("App Version: ", fontWeight = FontWeight.Bold)
                        Text(deviceInfo.getAppVersion())
                    }
                }
            }

            // 2. Battery Info Card
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Battery Status",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (isCharging) Icons.Default.BatteryChargingFull else Icons.Default.BatteryStd,
                            contentDescription = "Battery",
                            tint = when {
                                batteryLevel <= 20 -> Color.Red
                                batteryLevel <= 50 -> Color.Yellow
                                else -> Color.Green
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("$batteryLevel%", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isCharging) "Charging ⚡" else "Not Charging",
                            color = if (isCharging) Color(0xFF4CAF50) else Color.Gray
                        )
                    }
                }
            }
        }
    }
}