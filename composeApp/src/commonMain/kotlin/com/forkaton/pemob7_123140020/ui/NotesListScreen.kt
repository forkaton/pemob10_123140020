package com.forkaton.pemob7_123140020.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.forkaton.pemob7123140020.db.Note
import com.forkaton.pemob7_123140020.viewmodel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    appName: String = "Ansel Personal App", // Nama Aplikasi Baru Otomatis
    viewModel: NotesViewModel,
    onNavigateToAddNote: () -> Unit,
    onNavigateToEditNote: (Note) -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val notes by viewModel.notes.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(appName) },
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAddNote) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Catatan")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            // Kolom Pencarian
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.onSearchQueryChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Cari catatan...") },
                singleLine = true
            )

            if (notes.isEmpty()) {
                // UI Jika Kosong
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Tidak ada catatan ditemukan.", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                // 🌟 UI STAGGERED GRID (PINTEREST STYLE) 🌟
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2), // Dibagi menjadi 2 kolom
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp), // Jarak layar ke kartu
                    horizontalArrangement = Arrangement.spacedBy(12.dp), // Jarak horizontal antar kartu
                    verticalItemSpacing = 12.dp // Jarak vertikal antar kartu
                ) {
                    items(notes) { note ->
                        NoteCard(
                            note = note,
                            onClick = { onNavigateToEditNote(note) },
                            onDelete = { viewModel.deleteNote(note.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NoteCard(note: Note, onClick: () -> Unit, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        // Memberikan sentuhan warna kartu dari tema Navy yang kita buat
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        // Menggunakan Column agar teks tersusun ke bawah, bukan ke samping
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            // Judul Catatan
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary // Warna aksen (Biru Muda/Aqua)
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Isi Catatan (Dibatasi 6 baris agar grid terlihat bervariasi tingginya)
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Tombol Delete digeser ke pojok kanan bawah kartu
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(24.dp) // Ukuran ikon diperkecil sedikit agar elegan
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Hapus",
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}