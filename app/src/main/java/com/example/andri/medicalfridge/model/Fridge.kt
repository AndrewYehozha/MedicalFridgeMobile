package com.example.andri.medicalfridge.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Fridge(
    @SerializedName("IdFridge") val id: Int,
    @SerializedName("IdUser") val idUser: Int,
    @SerializedName("Status") val status: Boolean,
    @SerializedName("LastTemperature") val lastTemperature: Float,
    @SerializedName("LastHumidity") val lastHumidity: Float
) : Serializable