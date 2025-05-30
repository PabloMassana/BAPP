package com.falconteam.bapp.data.models

data class Alumno(
    val id: String = "",
    val nombreCompleto: String = "",
    val fechaNacimiento: String = "",
    val nivel: String = "",         // Ej: "Prekínder", "Inicial 1", etc.
    val padreId: String = "",       // Usuario (PADRE)
    val maestroId: String = "",     // Usuario (MAESTRO)
)
