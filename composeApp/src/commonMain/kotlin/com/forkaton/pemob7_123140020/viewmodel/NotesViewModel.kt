package com.forkaton.pemob7_123140020.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forkaton.pemob7123140020.db.Note
import com.forkaton.pemob7_123140020.local.NoteRepository
import com.forkaton.pemob7_123140020.local.SettingsManager // Import baru
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine // Import baru
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map // Import baru
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
// PERBAIKAN: Menambahkan settingsManager agar bisa membaca memori urutan (sort order)
class NotesViewModel(
    private val repository: NoteRepository,
    private val settingsManager: SettingsManager
) : ViewModel() {

    // 1. STATE PENCARIAN (Search Query)
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    // 2. STATE DAFTAR CATATAN (Otomatis memfilter & mengurutkan)
    val notes: StateFlow<List<Note>> = combine(
        _searchQuery,
        settingsManager.sortOrderFlow
    ) { query, sortOrder ->
        Pair(query, sortOrder) // Menggabungkan ketikan user dengan pilihan urutan
    }.flatMapLatest { (query, sortOrder) ->
        if (query.isBlank()) {
            // Jika pencarian kosong, ambil semua dan urutkan lewat Repository
            repository.getAllNotes(sortOrder)
        } else {
            // Jika ada pencarian, cari dulu kata-katanya, lalu urutkan hasilnya di sini
            repository.searchNotes(query).map { list ->
                when (sortOrder) {
                    "Oldest" -> list.sortedBy { it.created_at }
                    "A-Z" -> list.sortedBy { it.title.lowercase() }
                    "Z-A" -> list.sortedByDescending { it.title.lowercase() }
                    else -> list // Newest
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Fungsi untuk mengubah teks pencarian dari UI
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    // 3. FUNGSI CRUD
    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repository.insertNote(title, content)
        }
    }

    fun updateNote(id: Long, title: String, content: String) {
        viewModelScope.launch {
            repository.updateNote(id, title, content)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }
}