package com.falconteam.bapp.data.models

data class Actividad(
    val titulo: String,
    val fecha: String,
    val hora: String,
    val Imagenes: List<String> = emptyList()
)