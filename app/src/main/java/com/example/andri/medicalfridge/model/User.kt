package com.example.andri.medicalfridge.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("IdUser") val idUser: Int,
    @SerializedName("Login") val login: String,
    @SerializedName("Password") val password: String
) : Serializable