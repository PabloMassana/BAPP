package com.falconteam.bapp.data.models

data class Usuario(
    val id: String = "",
    val nombreCompleto: String = "",
    val email: String = "",
    val rol: RolUsuario = RolUsuario.PADRE,
    val hijosIds: List<String>? = null,       // Solo si es PADRE
    val alumnosAsignados: List<String>? = null // Solo si es MAESTRO
)

enum class RolUsuario {
    MAESTRO,
    PADRE
}
