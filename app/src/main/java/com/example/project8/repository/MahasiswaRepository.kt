package com.example.project8.repository

import com.example.project8.model.Mahasiswa
import com.example.project8.service_api.MahasiswaService
import okio.IOException

interface MahasiswaRepository {
    suspend fun getMahasiswa(): List<Mahasiswa>
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
    suspend fun updateMahasiswa(nim:String, mahasiswa: Mahasiswa)
    suspend fun deleteMahasiswa(nim: String)
    suspend fun getMahasiswaById(nim: String): Mahasiswa
}

