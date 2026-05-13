package com.forkaton.pemob7_123140020.local

import com.russhwolf.settings.MapSettings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SettingsManagerTest {

    private lateinit var mapSettings: MapSettings
    private lateinit var settingsManager: SettingsManager

    @Before
    fun setup() {
        mapSettings = MapSettings()
        settingsManager = SettingsManager(mapSettings)
    }

    @After
    fun tearDown() {
        mapSettings.clear()
    }

    @Test
    fun `test pengaturan tema mengubah dan membaca nilai dengan benar`() = runTest {
        // 1. Ubah nilai
        settingsManager.setTheme("Dark")
        // 2. Ambil nilai secara langsung (instan) dan cocokkan
        assertEquals("Dark", settingsManager.themeFlow.first())
    }

    @Test
    fun `test pengaturan urutan mengubah dan membaca nilai dengan benar`() = runTest {
        settingsManager.setSortOrder("Oldest")
        assertEquals("Oldest", settingsManager.sortOrderFlow.first())
    }

    @Test
    fun `test pengaturan ukuran font mengubah dan membaca nilai dengan benar`() = runTest {
        settingsManager.setFontSize(24)
        assertEquals(24, settingsManager.fontSizeFlow.first())
    }

    @Test
    fun `test pengaturan notifikasi mengubah dan membaca nilai dengan benar`() = runTest {
        settingsManager.setNotifications(false)
        assertEquals(false, settingsManager.notificationsFlow.first())
    }
}