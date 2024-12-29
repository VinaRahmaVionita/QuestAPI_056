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

//sebagai repository yang memanfaatkan service API untuk melakukan operasi
class NetworkMahasiswaRepository (
    private val MahasiswaApiService: MahasiswaService //untuk berkomunikasi dengan server melalui HTTP
) :MahasiswaRepository{
    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        MahasiswaApiService.insertMahasiswa(mahasiswa)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        MahasiswaApiService.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try{
            val response = MahasiswaApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful){
                throw IOException("Failed to delete mahasiswa. HTTP Status code: ${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getMahasiswa(): List<Mahasiswa> = MahasiswaApiService.getMahasiswa()
    override suspend fun getMahasiswaById(nim: String): Mahasiswa {
        return MahasiswaApiService.getMahasiswaById(nim)
    }
}