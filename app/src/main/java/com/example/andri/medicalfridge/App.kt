package com.example.andri.medicalfridge

import android.app.Application
import com.example.andri.medicalfridge.model.User
import com.example.andri.medicalfridge.network.Api
import com.example.andri.medicalfridge.network.Network

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Api = Network.createApi()
    }

    companion object {
        lateinit var Api: Api
        lateinit var user: User
    }

}