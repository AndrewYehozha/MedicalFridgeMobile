package com.example.andri.medicalfridge.network

import com.example.andri.medicalfridge.model.Fridge
import com.example.andri.medicalfridge.model.MainModel
import com.example.andri.medicalfridge.model.Medication
import com.example.andri.medicalfridge.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {

    @GET("api/Users/{login}/{password}")
    fun loginUser(@Path("login") login: String?, @Path("password") password: String?): Call<List<User>>

    @GET("api/Fridges/{id}")
    fun getFridgesByUserId(@Path("id") userId: Int): Call<List<Fridge>>

    @POST("api/Fridges")
    fun createChamber(@Body data: MainModel): Call<List<Fridge>>

    @GET("api/Medicaments/{id}")
    fun getMedications(@Path("id") idUser: Int): Call<List<Medication>>
}