package com.forkaton.pemob7_123140020.viewmodel

import app.cash.turbine.test
import com.forkaton.pemob7_123140020.local.NoteRepository
import com.forkaton.pemob7_123140020.local.SettingsManager
import com.forkaton.pemob7123140020.db.Note
import io.mockk.*
import kotlin.test.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class NotesViewModelTest {

    private val mockRepo = mockk<NoteRepository>(relaxed = true)
    private val mockSettings = mockk<SettingsManager>(relaxed = true)
    private lateinit var viewModel: NotesViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        
        // Mock default behavior for SettingsManager to avoid null flow
        every { mockSettings.sortOrderFlow } returns flowOf("Newest")
        every { mockRepo.getAllNotes("Newest") } returns flowOf(emptyList())
        
        viewModel = NotesViewModel(mockRepo, mockSettings)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    // ==========================================
    // 🧪 TES TURBINE (Uji StateFlow)
    // ==========================================

    @Test
    fun `(Turbine) searchQuery harus memancarkan nilai baru saat diubah`() = runTest {
        viewModel.searchQuery.test {
            assertEquals("", awaitItem()) // Nilai awal
            
            val query = "Cari saya"
            viewModel.onSearchQueryChanged(query)
            
            assertEquals(query, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `(Turbine) notes harus memancarkan daftar catatan dari repository`() = runTest {
        val fakeNotes = listOf(mockk<Note>(relaxed = true))
        every { mockRepo.getAllNotes("Newest") } returns flowOf(fakeNotes)
        
        val vm = NotesViewModel(mockRepo, mockSettings)
        
        vm.notes.test {
            // 1. Tangkap nilai awal (initial state) dari StateFlow (empty list)
            val initialState = awaitItem()
            assertEquals(emptyList<Note>(), initialState, "Pancaran pertama harus empty list")
            
            // 2. Tangkap nilai sebenarnya yang datang dari repository
            val actualNotes = awaitItem()
            assertEquals(fakeNotes, actualNotes, "Pancaran kedua harus berisi fakeNotes")
            
            cancelAndIgnoreRemainingEvents()
        }
    }

    // ==========================================
    // 🧪 TES MOCKK (Uji Interaksi Repository)
    // ==========================================

    @Test
    fun `(MockK) deleteNote harus memanggil repository deleteNote`() = runTest {
        val noteId = 123L
        coEvery { mockRepo.deleteNote(noteId) } just Runs

        viewModel.deleteNote(noteId)
        advanceUntilIdle()

        coVerify(exactly = 1) { mockRepo.deleteNote(noteId) }
    }

    @Test
    fun `(MockK) addNote harus memanggil repository insertNote`() = runTest {
        val title = "Judul Baru"
        val content = "Konten Baru"
        coEvery { mockRepo.insertNote(title, content) } just Runs

        viewModel.addNote(title, content)
        advanceUntilIdle()

        coVerify(exactly = 1) { mockRepo.insertNote(title, content) }
    }

    @Test
    fun `(MockK) updateNote harus memanggil repository updateNote`() = runTest {
        val id = 1L
        val title = "Update T"
        val content = "Update C"
        coEvery { mockRepo.updateNote(id, title, content) } just Runs

        viewModel.updateNote(id, title, content)
        advanceUntilIdle()

        coVerify(exactly = 1) { mockRepo.updateNote(id, title, content) }
    }
    
    @Test
    fun `(MockK) onSearchQueryChanged harus mengubah state searchQuery`() = runTest {
        val query = "Kotlin KMP"
        viewModel.onSearchQueryChanged(query)
        
        assertEquals(query, viewModel.searchQuery.value)
    }
}