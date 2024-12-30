package com.example.project8.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project8.model.Mahasiswa
import com.example.project8.repository.MahasiswaRepository
import kotlinx.coroutines.launch



fun UpdateUiEvent.toMahasiswaEntity(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)

data class UpdateUiState(
    val mahasiswaEvent: UpdateUiEvent = UpdateUiEvent(),
    val snackBarMessage: String? = null
)

data class UpdateUiEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)