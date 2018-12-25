package com.example.andri.medicalfridge.model

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import java.io.Serializable

data class Medication(
    @SerializedName("IdMedicament") val idMedication: Int,
    @SerializedName("IdFridge") val idFridge: Int,
    @SerializedName("Name") val name: String,
    @SerializedName("Amount") val amount: Int,
    @SerializedName("DataProduction") val dateProduction: DateTime,
    @SerializedName("ExpirationDate") val expirationDate: DateTime,
    @SerializedName("Price") val price: Float,
    @SerializedName("MinTemperature") val minTemperature: Float,
    @SerializedName("MaxTemperature") val maxTemperature: Float,
    @SerializedName("Status") val status: Boolean
) : Serializable