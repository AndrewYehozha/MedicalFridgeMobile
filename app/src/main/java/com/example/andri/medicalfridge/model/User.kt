package com.example.andri.medicalfridge.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("IdUser") val idUser: String,
    @SerializedName("Login") val login: String,
    @SerializedName("Password") val password: String,
    @SerializedName("NameOrganization") val nameOrganization: String,
    @SerializedName("Role") val role: String,
    @SerializedName("Country") val country: String,
    @SerializedName("City") val city: String,
    @SerializedName("Address") val address: String,
    @SerializedName("Phone") val phone: String
) : Serializable