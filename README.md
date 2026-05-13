# 📱 Tugas Praktikum 10 — Testing & Quality Assurance

<div align="center">

![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Testing](https://img.shields.io/badge/Testing-Unit_%26_UI-00B2FF?style=for-the-badge&logo=junit5&logoColor=white)
![MockK](https://img.shields.io/badge/MockK-Mocking_Library-6DB33F?style=for-the-badge)
![Coverage](https://img.shields.io/badge/Coverage-97.9%25-4CAF50?style=for-the-badge)

**IF25-22017 — Pengembangan Aplikasi Mobile**  
**Program Studi Teknik Informatika — Institut Teknologi Sumatera**  
**Tahun Akademik Genap 2025/2026**

---

| 👤 Identitas | Keterangan |
|---|---|
| 🎓 **Nama** | Anselmus Herpin Hasugian |
| 🆔 **NIM** | 123140020 |
| 📚 **Prodi** | Teknik Informatika |
| 🏫 **Institusi** | Institut Teknologi Sumatera (ITERA) |
| 📅 **Semester** | 6 |
| 🖥️ **Kelas** | Pengembangan Aplikasi Mobile — RB |
| 🌿 **Branch** | `week-10` |

[![Video Demo](https://img.shields.io/badge/Video_Demo-Google_Drive-4285F4?style=flat-square&logo=googledrive&logoColor=white)](LINK_DRIVE_VIDEO_KAMU_DISINI)

</div>

---

## 📋 Daftar Isi

- [Tentang Modul 10](#-tentang-modul-10)
- [Capaian Pembelajaran](#-capaian-pembelajaran)
- [Konsep Utama](#-konsep-utama)
- [Fitur yang Diuji (18 Test Cases)](#-fitur-yang-diuji-18-test-cases)
- [Struktur Project](#-struktur-project)
- [Rubrik Penilaian](#️-rubrik-penilaian)
- [Dokumentasi Pengujian & Coverage](#-dokumentasi-pengujian--coverage)

---

## 📖 Tentang Modul 10

Modul 10 berfokus pada **Quality Assurance** melalui penerapan **Testing** pada aplikasi Kotlin Multiplatform. Kita menguji setiap lapisan arsitektur (Data, Domain, ViewModel, dan UI) untuk memastikan aplikasi berjalan sesuai ekspektasi dan tahan terhadap *regression bugs*.

Fokus utama:
1. **Unit Testing** pada Repository, ViewModel, dan Local Settings.
2. **Mocking** dependencies menggunakan MockK.
3. **Flow Testing** menggunakan Turbine dan fungsi *first()*.
4. **UI Integration Testing** menggunakan Compose Test Rule.
5. **Coverage Measurement** mencapai **97.9%** (melampaui target 60%).

---

## 🎯 Capaian Pembelajaran

Setelah menyelesaikan modul ini, mahasiswa mampu:

- ✅ Melakukan refactoring Koin DI untuk mempermudah testing.
- ✅ Membuat Unit Test dengan pola **AAA (Arrange, Act, Assert)**.
- ✅ Melakukan **Mocking** pada dependency kompleks secara terisolasi.
- ✅ Menguji data stream (Flow/StateFlow) secara asinkron.
- ✅ Menulis **UI Test** untuk memvalidasi interaksi pengguna menggunakan `testTag`.
- ✅ Menganalisis dan mengoptimalkan laporan **Code Coverage**.

---

## 🚀 Fitur yang Diuji (18 Test Cases)

### 🧪 1. Unit Test: NoteRepositoryTest (5 Cases)

- `[✅] getAllNotes` — Memastikan pemanggilan query database.
- `[✅] searchNotes` — Memastikan parameter pencarian diteruskan.
- `[✅] insertNote` — Validasi proses penyimpanan data baru.
- `[✅] updateNote` — Validasi pembaruan data yang sudah ada.
- `[✅] deleteNote` — Validasi penghapusan data.

### 🧪 2. Unit Test: NotesViewModelTest (6 Cases)

- `[✅] (Turbine) searchQuery` — Memancarkan nilai baru saat diubah.
- `[✅] (Turbine) notes` — Memancarkan data dari repository (initial & actual).
- `[✅] (MockK) addNote` — Pemanggilan repository `insertNote`.
- `[✅] (MockK) updateNote` — Pemanggilan repository `updateNote`.
- `[✅] (MockK) deleteNote` — Pemanggilan repository `deleteNote`.
- `[✅] (MockK) onSearchQueryChanged` — Perubahan state pencarian.

### 🧪 3. Unit Test: SettingsManagerTest (4 Cases)

*Pengujian menggunakan `MapSettings` untuk validasi penyimpanan memori lokal.*

- `[✅] test pengaturan tema` — Mengubah dan membaca nilai `themeFlow`.
- `[✅] test pengaturan urutan` — Mengubah dan membaca nilai `sortOrderFlow`.
- `[✅] test pengaturan ukuran font` — Mengubah dan membaca nilai `fontSizeFlow`.
- `[✅] test pengaturan notifikasi` — Mengubah dan membaca nilai `notificationsFlow`.

### 📱 4. UI Test: NotesListScreenTest (3 Cases)

- `[✅] testJudulAplikasiTampil` — Validasi penampilan Judul Aplikasi "Ansel Personal App".
- `[✅] testTombolFabTambahMuncul` — Validasi keberadaan Tombol Tambah (FAB) menggunakan `testTag`.
- `[✅] testIkonSettingsTersedia` — Validasi keberadaan Ikon Settings pada TopAppBar.

---

## 📁 Struktur Project

```text
composeApp/src/
├── commonMain/         # Shared Code (Repository, ViewModel, UI)
├── androidUnitTest/    # Unit Tests (MockK, Turbine, MapSettings)
│   └── kotlin/com/forkaton/pemob7_123140020/
│       ├── repository/
│       │   └── NoteRepositoryTest.kt
│       ├── viewmodel/
│       │   └── NotesViewModelTest.kt
│       └── local/
│           └── SettingsManagerTest.kt
└── androidInstrumentedTest/  # UI Tests (Compose Rule)
    └── kotlin/com/forkaton/pemob7_123140020/
        └── ui/
            └── NotesListScreenTest.kt
```

---

## ✔️ Rubrik Penilaian

| No | Komponen | Bobot | Status | Bukti |
|---|---|---|---|---|
| 1 | Koin Refactor | 10% | ✔️ Selesai | `dataModule` & `viewModelModule` dipisahkan |
| 2 | Unit Test Repo | 20% | ✔️ Selesai | 5 test cases (`NoteRepositoryTest.kt`) |
| 3 | Unit Test VM | 35% | ✔️ Selesai | 6 test cases (`NotesViewModelTest.kt`) |
| 4 | UI Testing | 25% | ✔️ Selesai | 3 cases dengan Test Tags (`NotesListScreenTest.kt`) |
| 5 | Code Coverage | 10% | ✔️ Selesai | 97.9% Coverage untuk Business Logic |

---

## 📸 Dokumentasi Pengujian & Coverage

### 1. Hasil Eksekusi Unit Test (ViewModel, Repository, Settings)
![Unit Test Results](Capture/unit_test_results.png)

### 2. Hasil Eksekusi UI Test (Compose)
![UI Test Results](Capture/ui_test_results.png)

### 3. Laporan Code Coverage (97.9%)
![Coverage Summary](Capture/coverage_report.png)
![Coverage Details 1](Capture/coverage_details_1.png)
![Coverage Details 2](Capture/coverage_details_2.png)
