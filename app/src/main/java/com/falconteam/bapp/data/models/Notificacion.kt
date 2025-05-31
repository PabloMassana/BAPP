package com.falconteam.bapp.data.models

data class Notificacion(
    val id: String,
    val titulo: String,
    val cuerpo: String,
    val fecha: String,
    val tipo: String // "evento", "urgente", etc.
)
