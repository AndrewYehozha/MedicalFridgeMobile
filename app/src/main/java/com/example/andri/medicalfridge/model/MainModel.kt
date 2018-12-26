package com.example.andri.medicalfridge.model

import java.io.Serializable

data class MainModel(
    val IdFridge: Int = 0,
    val IdUser: Int
) : Serializable {
}