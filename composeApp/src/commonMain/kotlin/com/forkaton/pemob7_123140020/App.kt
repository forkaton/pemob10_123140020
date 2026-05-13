package com.forkaton.pemob7_123140020

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.forkaton.pemob7123140020.db.Note
import com.forkaton.pemob7_123140020.local.*
import com.forkaton.pemob7_123140020.ui.*
import com.forkaton.pemob7_123140020.viewmodel.NotesViewModel
import com.russhwolf.settings.Settings
import com.forkaton.pemob7_123140020.di.dataModule
import com.forkaton.pemob7_123140020.di.viewModelModule
import com.forkaton.pemob7_123140020.di.platformModule
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

enum class ScreenState { List, AddEdit, Settings }
@Composable
fun App() {
    KoinContext {
        val settingsManager: SettingsManager = koinInject()
        val viewModel: NotesViewModel = koinInject()

        var currentScreen by remember { mutableStateOf(ScreenState.List) }
        var selectedNoteToEdit by remember { mutableStateOf<Note?>(null) }

        // --- LOGIKA TEMA KUSTOM (NAVY OCEAN) ---
        val themePref by settingsManager.themeFlow.collectAsState(initial = "System")

        // Palet Warna Navy Ocean (Unik & Profesional)
        val navyColorScheme = darkColorScheme(
            primary = Color(0xFF64FFDA),       // Aqua/Biru Muda (Aksen)
            secondary = Color(0xFF172A45),     // Deep Blue
            background = Color(0xFF0A192F),    // Navy Gelap (Latar)
            surface = Color(0xFF112240),       // Navy Sedang (Kartu)
            onBackground = Color.White,
            onSurface = Color.White
        )

        val colorScheme = when (themePref) {
            "Navy" -> navyColorScheme
            "Dark" -> darkColorScheme()
            "Light" -> lightColorScheme()
            else -> if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
        }

        MaterialTheme(colorScheme = colorScheme) {
            // Gunakan Scaffold atau Header dengan Nama Baru
            Surface(color = MaterialTheme.colorScheme.background) {
                Column {
                    when (currentScreen) {
                        ScreenState.List -> {
                            NotesListScreen(
                                appName = "Ansel Personal App",
                                viewModel = viewModel,
                                onNavigateToAddNote = {
                                    selectedNoteToEdit = null
                                    currentScreen = ScreenState.AddEdit
                                },
                                onNavigateToEditNote = { note ->
                                    selectedNoteToEdit = note
                                    currentScreen = ScreenState.AddEdit
                                },
                                onNavigateToSettings = { currentScreen = ScreenState.Settings }
                            )
                        }
                        ScreenState.AddEdit -> {
                            AddEditNoteScreen(
                                viewModel = viewModel,
                                noteToEdit = selectedNoteToEdit,
                                onNavigateBack = { currentScreen = ScreenState.List }
                            )
                        }

                        ScreenState.Settings -> {
                            SettingsScreen(
                                settingsManager = settingsManager,
                                onNavigateBack = { currentScreen = ScreenState.List }
                            )
                        }
                    }
                }
            }
        }
    }
}