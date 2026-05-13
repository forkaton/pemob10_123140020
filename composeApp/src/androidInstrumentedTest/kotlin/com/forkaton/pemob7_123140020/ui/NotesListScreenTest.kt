package com.forkaton.pemob7_123140020.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class NotesListScreenTest {

    // Inisialisasi Rule wajib untuk menjalankan Compose UI Testing
    @get:Rule
    val composeTestRule = createComposeRule()

    // --- TEST CASE 1: Uji Judul Aplikasi ---
    @Test
    fun testJudulAplikasiTampil() {
        composeTestRule.setContent {
            NotesListScreen(
                onNavigateToAddNote = {},
                onNavigateToEditNote = { _ -> },
                onNavigateToSettings = {}
            )
        }

        // Assert: Cari teks secara spesifik dan pastikan ia tampil di layar
        composeTestRule.onNodeWithText("Ansel Personal App").assertIsDisplayed()
    }

    // --- TEST CASE 2: Uji Tombol Tambah Catatan (FAB) ---
    @Test
    fun testTombolFabTambahMuncul() {
        composeTestRule.setContent {
            NotesListScreen(
                onNavigateToAddNote = {},
                onNavigateToEditNote = { _ -> },
                onNavigateToSettings = {}
            )
        }

        // 🌟 Solusi Level Pro: Cari berdasarkan tag "add_button" alih-alih teks deskripsi
        composeTestRule.onNodeWithTag("add_button").assertIsDisplayed()
    }

    // --- TEST CASE 3: Uji Ikon Pengaturan (Settings) ---
    @Test
    fun testIkonSettingsTersedia() {
        composeTestRule.setContent {
            NotesListScreen(
                onNavigateToAddNote = {},
                onNavigateToEditNote = { _ -> },
                onNavigateToSettings = {}
            )
        }

        // Assert: Cari ikon yang memiliki deskripsi konten "Settings"
        composeTestRule.onNodeWithContentDescription("Settings").assertIsDisplayed()
    }
}