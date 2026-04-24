package com.forkaton.pemob7_123140020.local

import com.russhwolf.settings.Settings
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import kotlinx.coroutines.flow.Flow

class SettingsManager(settings: Settings) {

    // 1. Deklarasi Jembatan (Wajib di paling atas agar bisa dibaca ke bawah)
    // Melakukan cast ke ObservableSettings agar bisa diubah menjadi FlowSettings
    private val flowSettings = (settings as ObservableSettings).toFlowSettings()

    // 2. Kumpulan Kunci (Digabung jadi satu Companion Object)
    companion object {
        private const val KEY_THEME = "theme"
        private const val KEY_SORT_ORDER = "sort_order"
        private const val KEY_FONT_SIZE = "font_size"
        private const val KEY_NOTIFICATIONS = "notifications"
    }

    // --- FITUR PENGATURAN ---

    // Pengaturan Tema (Light, Dark, System)
    val themeFlow: Flow<String> = flowSettings.getStringFlow(KEY_THEME, "System")
    suspend fun setTheme(theme: String) {
        flowSettings.putString(KEY_THEME, theme)
    }

    // Pengaturan Urutan Catatan (Newest, Oldest, A-Z)
    val sortOrderFlow: Flow<String> = flowSettings.getStringFlow(KEY_SORT_ORDER, "Newest")
    suspend fun setSortOrder(order: String) {
        flowSettings.putString(KEY_SORT_ORDER, order)
    }

    // Pengaturan Ukuran Font
    val fontSizeFlow: Flow<Int> = flowSettings.getIntFlow(KEY_FONT_SIZE, 16)
    suspend fun setFontSize(size: Int) {
        flowSettings.putInt(KEY_FONT_SIZE, size)
    }

    // Pengaturan Notifikasi
    val notificationsFlow: Flow<Boolean> = flowSettings.getBooleanFlow(KEY_NOTIFICATIONS, true)
    suspend fun setNotifications(enabled: Boolean) {
        flowSettings.putBoolean(KEY_NOTIFICATIONS, enabled)
    }
}