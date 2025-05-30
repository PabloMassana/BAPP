package com.falconteam.bapp.utils

object RolUtils {
    fun obtenerRolDesdeEmail(email: String): String {
        return if (email.contains("admin")) "Administrador" else "Usuario"
    }
}
