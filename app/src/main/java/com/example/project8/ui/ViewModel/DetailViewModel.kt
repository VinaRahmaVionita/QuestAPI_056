package com.example.project8.ui.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project8.model.Mahasiswa
import com.example.project8.repository.MahasiswaRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.example.project8.ui.view.DestinasiDetail



fun Mahasiswa.toDetailUiEvent(): InsertUiEvent{
    return InsertUiEvent(
        nim = nim,
        nama = nama,
        jenisKelamin = jenisKelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan
    )
}