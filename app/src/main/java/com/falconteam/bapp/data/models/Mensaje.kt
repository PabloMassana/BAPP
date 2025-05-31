package com.falconteam.bapp.data.models

data class Mensaje(
    val id: String,
    val conversacionId: String,
    val emisorId: String,
    val contenido: String,
    val tipo: String = "texto", // "texto", "imagen", "urgente"
    val timestamp: String
)
