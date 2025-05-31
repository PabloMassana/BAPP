package com.falconteam.bapp.data.models

data class Bitacora(
    val id: String,
    val fecha: String,
    val idAlumno: String,
    val comida: String,
    val descanso: String,
    val aprendizaje: String,
    val comportamiento: String,
    val imagenes: List<String> = emptyList()
)
