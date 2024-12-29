package com.example.project8.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project8.model.Mahasiswa
import com.example.project8.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertViewModel(private val mhs: MahasiswaRepository): ViewModel(){
    var uiState by mutableStateOf(InsertUiState())
        private set
    fun updateInsertMhsState(insertUiEvent: InsertUiEvent){
        uiState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun insertMhs(){
        viewModelScope.launch {
            try {
                mhs.insertMahasiswa(uiState.insertUiEvent.toMhs())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}

fun Mahasiswa.toUiStateMhs(): InsertUiState = InsertUiState(
    insertUiEvent = toInsertUiEvent()
)

fun InsertUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)

fun Mahasiswa.toInsertUiEvent(): InsertUiEvent = InsertUiEvent(
    nim = nim,
    nama = nama,
    alamat = alamat,
    jenisKelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)

data class InsertUiEvent(
    val nim: String = "",
    val nama: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)