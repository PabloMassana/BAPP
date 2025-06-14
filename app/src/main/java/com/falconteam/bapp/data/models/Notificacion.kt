package com.falconteam.bapp.data.models

data class Notificacion(
    val id: String,
    val titulo: String,
    val cuerpo: String,
    val fecha: String,
    val tipo: String,
    val leida: Boolean = false, // Sugerido para marcar como leída
    val destinatario_id: String // Para filtrar por usuario
)
