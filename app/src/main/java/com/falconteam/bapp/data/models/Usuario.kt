package com.falconteam.bapp.data.models

enum class Rol { ADMIN, MAESTRO, PADRE }

data class Usuario(
    val id: String,
    val nombre: String,
    val email: String,
    val rol: Rol,
    val cursosInscritos: List<String> = emptyList(), // cursos si es padre o maestro
    val hijos: List<String> = emptyList() // si es padre, IDs de alumnos
)
