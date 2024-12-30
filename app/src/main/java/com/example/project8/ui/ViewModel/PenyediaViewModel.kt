package com.example.project8.ui.ViewModel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.project8.MahasiswaApplications


object PenyediaViewModel {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    val Factory =
        viewModelFactory {
            initializer {
                HomeViewModel(
                    mahasiswaApplication().container.mahasiswaRepository
                )
            }
            initializer {
                InsertViewModel(
                    mahasiswaApplication().container.mahasiswaRepository
                )
            }
            initializer {
                DetailViewModel(
                    createSavedStateHandle(),
                    mahasiswaApplication().container.mahasiswaRepository
                )
            }
            initializer {
                UpdateViewModel(
                    savedStateHandle = createSavedStateHandle(),
                    repository = mahasiswaApplication().container.mahasiswaRepository,
                )
            }
        }
}
fun CreationExtras.mahasiswaApplication(): MahasiswaApplications = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MahasiswaApplications)