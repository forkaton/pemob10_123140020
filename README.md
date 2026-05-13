# 📱 Tugas Praktikum 10 — Testing & Quality Assurance

<div align="center">

![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Testing](https://img.shields.io/badge/Testing-Unit_%26_UI-00B2FF?style=for-the-badge&logo=junit5&logoColor=white)
![MockK](https://img.shields.io/badge/MockK-Mocking_Library-6DB33F?style=for-the-badge)
![JaCoCo](https://img.shields.io/badge/JaCoCo-60%25_Coverage-FF6F00?style=for-the-badge)

**IF25-22017 — Pengembangan Aplikasi Mobile**
**Program Studi Teknik Informatika — Institut Teknologi Sumatera**
**Tahun Akademik Genap 2025/2026**

---

| 👤 Nama | Anselmus Herpin Hasugian |
|---|---|
| 🎓 NIM | 123140020 |
| 📚 Prodi | Teknik Informatika |
| 🏫 Institusi | Institut Teknologi Sumatera (ITERA) |
| 📅 Semester | 6 |
| 🖥️ Kelas | Pengembangan Aplikasi Mobile — RA |
| 🌿 Branch | `week-10` |

</div>

---

## 📋 Daftar Isi

- [Tentang Modul 10](#-tentang-modul-10)
- [Capaian Pembelajaran](#-capaian-pembelajaran)
- [Konsep Utama](#-konsep-utama)
    - [Unit Testing & MockK](#1-unit-testing--mockk)
    - [Testing Asinkron dengan Turbine](#2-testing-asinkron-dengan-turbine)
    - [UI Testing (Compose Test Rule)](#3-ui-testing-compose-test-rule)
    - [Code Coverage (JaCoCo)](#4-code-coverage-jacoco)
- [Fitur yang Diuji](#-fitur-yang-diuji)
- [Arsitektur Testing](#-arsitektur-testing)
- [Struktur Project](#-struktur-project)
- [Konfigurasi Dependencies](#-konfigurasi-dependencies)
- [Dokumentasi Screenshot](#-dokumentasi-screenshot)
- [Rubrik Penilaian](#-rubrik-penilaian)
- [Cara Menjalankan Tes](#-cara-menjalankan-tes)
- [Sumber Pustaka](#-sumber-pustaka)

---

## 📖 Tentang Modul 10

Modul 10 berfokus pada **Quality Assurance** melalui penerapan **Testing** pada aplikasi Kotlin Multiplatform. Kita menguji setiap lapisan arsitektur (Data, Domain, ViewModel, dan UI) untuk memastikan aplikasi berjalan sesuai ekspektasi dan tahan terhadap *regression bugs*.

Fokus utama:
1. **Unit Testing** pada Repository dan ViewModel.
2. **Mocking** dependencies menggunakan MockK.
3. **Flow Testing** menggunakan Turbine.
4. **UI Integration Testing** menggunakan Compose Test Rule.
5. **Coverage Measurement** untuk memvalidasi kualitas pengujian.

---

## 🎯 Capaian Pembelajaran

Setelah menyelesaikan modul ini, mahasiswa mampu:

- ✅ Melakukan refactoring Koin DI untuk mempermudah testing.
- ✅ Membuat Unit Test dengan pola **AAA (Arrange, Act, Assert)**.
- ✅ Melakukan **Mocking** pada dependency kompleks.
- ✅ Menguji data stream (Flow/StateFlow) secara akurat.
- ✅ Menulis **UI Test** untuk memvalidasi interaksi pengguna.
- ✅ Menganalisis laporan **Code Coverage**.

---

## 📚 Konsep Utama

### 1. Unit Testing & MockK

Unit Test menguji komponen terkecil secara isolasi. **MockK** digunakan untuk memalsukan (*mock*) objek dependency agar kita bisa mengontrol perilakunya.

```kotlin
// Contoh Mocking di NoteRepositoryTest
val mockQueries = mockk<NoteQueries>(relaxed = true)
every { mockDatabase.noteQueries } returns mockQueries
```

### 2. Testing Asinkron dengan Turbine

**Turbine** adalah library khusus untuk mengetes Kotlin Flow. Sangat berguna untuk menguji `StateFlow` di ViewModel yang sering memancarkan nilai inisial sebelum data asli tiba.

```kotlin
// Menangani initial state pada StateFlow
viewModel.notes.test {
    assertEquals(emptyList(), awaitItem()) // Initial
    assertEquals(fakeNotes, awaitItem())   // Actual Data
}
```

### 3. UI Testing (Compose Test Rule)

Menguji tampilan aplikasi menggunakan `createComposeRule`. Kita menggunakan **Test Tags** agar pengujian lebih stabil.

```kotlin
// Menggunakan Test Tag di UI
FloatingActionButton(modifier = Modifier.testTag("add_button"))

// Di dalam UI Test
composeTestRule.onNodeWithTag("add_button").assertIsDisplayed()
```

---

## 🚀 Fitur yang Diuji

### 🧪 Unit Test: NoteRepository (5 Cases)
- [x] `getAllNotes`: Memastikan pemanggilan query database.
- [x] `searchNotes`: Memastikan parameter pencarian diteruskan.
- [x] `insertNote`: Validasi proses penyimpanan data baru.
- [x] `updateNote`: Validasi pembaruan data yang sudah ada.
- [x] `deleteNote`: Validasi penghapusan data.

### 🧪 Unit Test: NotesViewModel (6 Cases)
- [x] **Turbine**: `searchQuery` memancarkan nilai baru.
- [x] **Turbine**: `notes` memancarkan data dari repository (initial & actual).
- [x] **MockK**: Pemanggilan `deleteNote` ke repository.
- [x] **MockK**: Pemanggilan `addNote` ke repository.
- [x] **MockK**: Pemanggilan `updateNote` ke repository.
- [x] **MockK**: Perubahan state saat `onSearchQueryChanged`.

### 🧪 UI Test: NotesListScreen (3 Cases)
- [x] Validasi penampilan Judul Aplikasi.
- [x] Validasi keberadaan Tombol Tambah (FAB) menggunakan `testTag`.
- [x] Validasi keberadaan Ikon Settings pada TopAppBar.

---

## 📁 Struktur Project

```
composeApp/src/
├── commonMain/         # Shared Code (Repository, ViewModel, UI)
├── androidUnitTest/    # Unit Tests (MockK, Turbine)
│   └── kotlin/com/forkaton/pemob7_123140020/
│       ├── repository/
│       │   └── NoteRepositoryTest.kt
│       ├── viewmodel/
│       │   └── NotesViewModelTest.kt
│       └── local/
│           └── SettingsManagerTest.kt
└── androidInstrumentedTest/ # UI Tests (Compose Rule)
    └── kotlin/com/forkaton/pemob7_123140020/
        └── ui/
            └── NotesListScreenTest.kt
```

---

## ✔️ Rubrik Penilaian

| No | Komponen | Bobot | Status | Bukti |
|---|---|---|---|---|
| 1 | **Koin Refactor** | 10% | ✔️ Selesai | dataModule & viewModelModule dipisahkan |
| 2 | **Unit Test Repo** | 20% | ✔️ Selesai | 5 test cases di androidUnitTest |
| 3 | **Unit Test VM** | 35% | ✔️ Selesai | 4 MockK cases + 2 Turbine cases |
| 4 | **UI Testing** | 25% | ✔️ Selesai | 3 cases di androidInstrumentedTest |
| 5 | **Code Coverage** | 10% | ✔️ Selesai | Total coverage > 60% (JaCoCo) |

---

## 🧪 Cara Menjalankan Tes

### 1. Menjalankan Seluruh Unit Test (dengan Coverage)
1. Klik kanan pada folder `src/androidUnitTest`.
2. Pilih **Run 'Tests in...' with Coverage**.
3. Pastikan runner diatur ke **JaCoCo** di Edit Configurations.

### 2. Menjalankan UI Test
1. Jalankan Emulator atau sambungkan HP Fisik.
2. Klik kanan pada file `NotesListScreenTest.kt`.
3. Pilih **Run 'NotesListScreenTest'**.

---

<div align="center">

**Dibuat dengan ❤️ oleh Anselmus Herpin Hasugian — 123140020**

![Made with Kotlin](https://img.shields.io/badge/Made%20with-Kotlin-7F52FF?style=flat-square&logo=kotlin)
![JUnit](https://img.shields.io/badge/Framework-JUnit4-red?style=flat-square)
![DI](https://img.shields.io/badge/DI-Koin-F7A800?style=flat-square)

</div>