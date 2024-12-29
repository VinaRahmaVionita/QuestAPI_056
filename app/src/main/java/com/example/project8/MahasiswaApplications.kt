package com.example.project8

import android.app.Application
import com.example.project8.repository.AppContainer
import com.example.project8.repository.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}