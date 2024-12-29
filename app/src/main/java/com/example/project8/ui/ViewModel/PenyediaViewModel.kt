package com.example.project8.ui.ViewModel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.project8.MahasiswaApplications


object PenyediaViewModel {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    val Factory =
        viewModelFactory {
            initializer { HomeViewModel(aplikasiKontak().container.mahasiswaRepository) }
            initializer { InsertViewModel(aplikasiKontak().container.mahasiswaRepository) }
        }
}
fun CreationExtras.aplikasiKontak(): MahasiswaApplications = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplications)