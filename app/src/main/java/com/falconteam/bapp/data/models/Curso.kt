package com.falconteam.bapp.data.models

data class Curso(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val idMaestro: String,
    val alumnos: List<String> = emptyList()
)
