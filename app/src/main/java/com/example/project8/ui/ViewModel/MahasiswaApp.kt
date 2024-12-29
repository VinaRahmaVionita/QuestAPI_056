package com.example.project8.ui.ViewModel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.project8.ui.navigasi.PengelolaHalaman

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//pintu masuk utama untuk menampilkan dan mengelola seluruh tampilan aplikasi
fun MahasiswaApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.
        nestedScroll(scrollBehavior.nestedScrollConnection),
    // topBar = { TopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            PengelolaHalaman()
        }
    }
}