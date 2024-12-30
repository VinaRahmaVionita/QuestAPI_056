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

class UpdateViewModel(
    private val repository: MahasiswaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf(UpdateUiState())
        private set

    private val _nim: String = checkNotNull(savedStateHandle["nim"])

    init {
        getMahasiswaDetail()
    }

    private fun getMahasiswaDetail() {
        viewModelScope.launch {
            try {
                val mahasiswa = repository.getMahasiswaById(_nim)
                uiState = UpdateUiState(mahasiswaEvent = mahasiswa.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateMahasiswaState(mahasiswaEvent: UpdateUiEvent) {
        uiState = uiState.copy(mahasiswaEvent = mahasiswaEvent)
    }

    fun updateData() {
        val currentEvent = uiState.mahasiswaEvent

        viewModelScope.launch {
            try {
                repository.updateMahasiswa(currentEvent.nim, currentEvent.toMahasiswaEntity())
                uiState = uiState.copy(
                    snackBarMessage = "Data berhasil diupdate",
                    mahasiswaEvent = UpdateUiEvent()
                )
            } catch (e: Exception) {
                uiState = uiState.copy(
                    snackBarMessage = "Data gagal diupdate"
                )
            }
        }
    }

    fun resetSnackBarMessage() {
        uiState = uiState.copy(snackBarMessage = null)
    }
}

fun Mahasiswa.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)

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