package com.forkaton.pemob7_123140020.repository

import com.forkaton.pemob7_123140020.AppDatabase
import com.forkaton.pemob7_123140020.local.NoteRepository
import com.forkaton.pemob7123140020.db.NoteQueries
import io.mockk.*
import kotlin.test.*
import kotlinx.coroutines.test.runTest

class NoteRepositoryTest {

    // 🌟 1. MOCK DEPENDENSINYA (DATABASE)
    private val mockDatabase = mockk<AppDatabase>(relaxed = true)
    private val mockQueries = mockk<NoteQueries>(relaxed = true)
    
    // 🌟 2. KITA BUAT REPOSITORY ASLI
    private lateinit var repository: NoteRepository

    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        
        // Pastikan database mengembalikan mock queries
        every { mockDatabase.noteQueries } returns mockQueries
        
        // 🌟 3. MASUKKAN MOCK DATABASE KE DALAM REPOSITORY ASLI
        repository = NoteRepository(mockDatabase) 
    }

    @AfterTest
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getAllNotes harus memanggil selectAll pada queries`() = runTest {
        val sortOrder = "Newest"
        
        // Act
        repository.getAllNotes(sortOrder)

        // Assert
        verify { mockQueries.selectAll() }
    }

    @Test
    fun `searchNotes harus memanggil search pada queries`() = runTest {
        val query = "test"
        
        // Act
        repository.searchNotes(query)

        // Assert
        verify { mockQueries.search(query) }
    }

    @Test
    fun `insertNote harus memanggil insert pada queries`() = runTest {
        val title = "Tugas KMP"
        val content = "Belajar Unit Test"

        // Act
        repository.insertNote(title, content)

        // Assert
        verify { mockQueries.insert(title, content, any()) }
    }

    @Test
    fun `updateNote harus memanggil update pada queries`() = runTest {
        val testId = 1L
        val title = "Judul"
        val content = "Konten"

        // Act
        repository.updateNote(testId, title, content)

        // Assert
        verify { mockQueries.update(title, content, testId) }
    }

    @Test
    fun `deleteNote harus memanggil delete pada queries`() = runTest {
        val testId = 1L

        // Act
        repository.deleteNote(testId)

        // Assert
        verify { mockQueries.delete(testId) }
    }
}
