package com.falconteam.bapp.data.models

data class Notificacion(
    val id: String = "",
    val titulo: String = "",
    val contenido: String = "",
    val fecha: String = "", // Timestamp o ISO
    val tipo: TipoNotificacion = TipoNotificacion.GENERAL
)

enum class TipoNotificacion {
    GENERAL,
    URGENTE,
    EVENTO
}
