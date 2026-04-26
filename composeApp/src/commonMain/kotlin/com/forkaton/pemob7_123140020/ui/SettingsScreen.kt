package com.forkaton.pemob7_123140020.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.forkaton.pemob7_123140020.local.SettingsManager
import com.forkaton.pemob7_123140020.ui.DeviceInfoScreen
import com.forkaton.pemob7_123140020.ui.BatteryInfoScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    settingsManager: SettingsManager,
    onNavigateBack: () -> Unit
) {
    // Coroutine untuk menjalankan fungsi penyimpanan DataStore
    val coroutineScope = rememberCoroutineScope()

    // Membaca status pengaturan saat ini secara real-time
    val currentTheme by settingsManager.themeFlow.collectAsState(initial = "Navy")
    val currentSortOrder by settingsManager.sortOrderFlow.collectAsState(initial = "Newest")

    // Daftar Pilihan yang sesuai dengan logika App.kt dan NoteRepository.kt
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
                .padding(16.dp)
        ) {
            // --- BAGIAN 1: PENGATURAN TEMA ---
            Text(
                text = "Tema Aplikasi",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            themes.forEach { themeOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch { settingsManager.setTheme(themeOption) }
                        }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = currentTheme == themeOption,
                        onClick = { coroutineScope.launch { settingsManager.setTheme(themeOption) } }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = if (themeOption == "Navy") "Navy Ocean (Eksklusif)" else themeOption)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            // --- BAGIAN 2: PENGATURAN URUTAN (SORT ORDER) ---
            Text(
                text = "Urutkan Catatan",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            sortOrders.forEach { sortOption ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            coroutineScope.launch { settingsManager.setSortOrder(sortOption) }
                        }
                        .padding(vertical = 8.dp)
                ) {
                    RadioButton(
                        selected = currentSortOrder == sortOption,
                        onClick = { coroutineScope.launch { settingsManager.setSortOrder(sortOption) } }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when(sortOption) {
                            "Newest" -> "Terbaru (Newest)"
                            "Oldest" -> "Terlama (Oldest)"
                            "A-Z" -> "A - Z (Alfabet)"
                            "Z-A" -> "Z - A (Z ke A)"
                            else -> sortOption
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            // Info Perangkat (Tugas Utama)
            DeviceInfoScreen()
            
            // Info Baterai (Bonus 10%) 🌟
            BatteryInfoScreen()
        }
    }
}