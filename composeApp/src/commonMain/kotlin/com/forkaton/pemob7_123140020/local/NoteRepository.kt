package com.forkaton.pemob7_123140020.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.forkaton.pemob7123140020.db.Note
import com.forkaton.pemob7_123140020.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NoteRepository(database: AppDatabase) {

    private val queries = database.noteQueries

    // READ & SORTING: Mengambil semua catatan dan langsung mengurutkannya
    fun getAllNotes(sortOrder: String): Flow<List<Note>> {
        return queries.selectAll().asFlow().mapToList(Dispatchers.IO).map { list ->
            when (sortOrder) {
                "Oldest" -> list.sortedBy { it.created_at }
                "A-Z" -> list.sortedBy { it.title.lowercase() }
                "Z-A" -> list.sortedByDescending { it.title.lowercase() }
                else -> list // Newest
            }
        }
    }

    // SEARCH: Mencari catatan
    fun searchNotes(query: String): Flow<List<Note>> {
        return queries.search(query)
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    // CREATE
    suspend fun insertNote(title: String, content: String) {
        withContext(Dispatchers.IO) {
            val now = kotlin.time.Clock.System.now().toEpochMilliseconds()
            queries.insert(title, content, now)
        }
    }

    // UPDATE
    suspend fun updateNote(id: Long, title: String, content: String) {
        withContext(Dispatchers.IO) {
            queries.update(title = title, content = content, id = id)
        }
    }

    // DELETE
    suspend fun deleteNote(id: Long) {
        withContext(Dispatchers.IO) {
            queries.delete(id)
        }
    }
}