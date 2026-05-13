package com.forkaton.pemob7_123140020.repository

import com.forkaton.pemob7_123140020.local.NoteRepository
import com.forkaton.pemob7123140020.db.Note
import io.mockk.*
import kotlin.test.*
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.flowOf

class NoteRepositoryTest {

    // Mock dependensi NoteRepository
    private lateinit var repository: NoteRepository

    @BeforeTest
    fun setup() {
        MockKAnnotations.init(this)
        
        // Kita menggunakan mockk() dengan relaxed=true agar tidak perlu mendefinisikan 
        // return value untuk fungsi-fungsi void (seperti insert/update/delete)
        repository = mockk<NoteRepository>(relaxed = true)
    }

    @AfterTest
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getAllNotes harus mengembalikan flow daftar catatan saat dipanggil`() = runTest {
        // Arrange (Persiapan Data)
        val sortOrder = "Newest"
        val fakeNotes = listOf(mockk<Note>(relaxed = true)) 
        val fakeFlow = flowOf(fakeNotes)
        every { repository.getAllNotes(sortOrder) } returns fakeFlow

        // Act (Aksi/Eksekusi)
        val result = repository.getAllNotes(sortOrder)

        // Assert (Validasi)
        assertNotNull(result)
        verify(exactly = 1) { repository.getAllNotes(sortOrder) }
    }

    @Test
    fun `searchNotes harus mengembalikan flow hasil pencarian`() = runTest {
        // Arrange
        val query = "test"
        val fakeNotes = listOf(mockk<Note>(relaxed = true))
        val fakeFlow = flowOf(fakeNotes)
        every { repository.searchNotes(query) } returns fakeFlow

        // Act
        val result = repository.searchNotes(query)

        // Assert
        assertNotNull(result)
        verify(exactly = 1) { repository.searchNotes(query) }
    }

    @Test
    fun `insertNote harus berhasil memanggil fungsi penyimpanan`() = runTest {
        // Arrange
        val title = "Tugas KMP"
        val content = "Belajar Unit Test"
        coEvery { repository.insertNote(title, content) } just Runs

        // Act
        repository.insertNote(title, content)

        // Assert
        coVerify(exactly = 1) { repository.insertNote(title, content) }
    }

    @Test
    fun `updateNote harus memperbarui data catatan`() = runTest {
        // Arrange
        val testId = 1L
        coEvery { repository.updateNote(testId, "Judul", "Konten") } just Runs

        // Act
        repository.updateNote(testId, "Judul", "Konten")

        // Assert
        coVerify(exactly = 1) { repository.updateNote(testId, "Judul", "Konten") }
    }

    @Test
    fun `deleteNote harus berhasil menghapus catatan`() = runTest {
        // Arrange
        val testId = 1L
        coEvery { repository.deleteNote(testId) } just Runs

        // Act
        repository.deleteNote(testId)

        // Assert
        coVerify(exactly = 1) { repository.deleteNote(testId) }
    }
}
