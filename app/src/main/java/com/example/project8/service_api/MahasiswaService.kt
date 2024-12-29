package com.example.project8.service_api

import com.example.project8.model.Mahasiswa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface MahasiswaService {

    //Menyediakan header HTTP statis yang dikirimkan bersama setiap permintaan
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("bacamahasiswa.php")
    suspend fun getMahasiswa(): List<Mahasiswa>

    @GET("baca1mahasiswa.php/{nim}")
    suspend fun getMahasiswaById(@Query("nim") nim:String):Mahasiswa

    @POST("insertmahasiswa.php")
    suspend fun insertMahasiswa(@Body mahasiswa:Mahasiswa)

    @PUT("editmahasiswa.php/{nim}")
    suspend fun updateMahasiswa(@Query("nim")nim:String, @Body mahasiswa: Mahasiswa)

    @DELETE("delelemahasiswa.php/{nim}")
    suspend fun deleteMahasiswa(@Query("nim")nim:String):Response<Void>
}