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

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val mahasiswaRepository: MahasiswaRepository
) : ViewModel() {
    private val nim: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState())
        private set

    init {
        getMahasiswaById()
    }

    private fun getMahasiswaById() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                val result = mahasiswaRepository.getMahasiswaById(nim)
                detailUiState = DetailUiState(
                    detailUiEvent = result.toDetailUiEvent(),
                    isLoading = false
                )
            } catch (e: Exception) {
                detailUiState = DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown"
                )
            }
        }
    }

    fun deleteMhs() {
        viewModelScope.launch {
            detailUiState = DetailUiState(isLoading = true)
            try {
                mahasiswaRepository.deleteMahasiswa(nim)

                detailUiState = DetailUiState(isLoading = false)
            } catch (e: Exception) {
                detailUiState = DetailUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown Error"
                )
            }
        }
    }
}


data class DetailUiState(
    val detailUiEvent: InsertUiEvent = InsertUiEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailUiEvent == InsertUiEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailUiEvent != InsertUiEvent()
}

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