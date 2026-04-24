package com.forkaton.pemob7_123140020.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.forkaton.pemob7123140020.db.Note
import com.forkaton.pemob7_123140020.viewmodel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditNoteScreen(
    viewModel: NotesViewModel,
    noteToEdit: Note? = null, // Null = Tambah Baru, Ada Isi = Edit Lama
    onNavigateBack: () -> Unit
) {
    // Mengisi otomatis form jika sedang mode Edit
    var title by remember { mutableStateOf(noteToEdit?.title ?: "") }
    var content by remember { mutableStateOf(noteToEdit?.content ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (noteToEdit == null) "Catatan Baru" else "Edit Catatan") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (title.isNotBlank() && content.isNotBlank()) {
                        if (noteToEdit == null) {
                            viewModel.addNote(title, content) // Create
                        } else {
                            viewModel.updateNote(noteToEdit.id, title, content) // Update
                        }
                        onNavigateBack() // Kembali ke layar utama setelah simpan
                    }
                }
            ) {
                Icon(Icons.Default.Check, contentDescription = "Simpan")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Judul Catatan") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Isi Catatan") },
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
        }
    }
}