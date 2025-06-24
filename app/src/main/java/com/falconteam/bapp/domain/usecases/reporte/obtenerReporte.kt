package com.falconteam.bapp.domain.usecases.reporte

fun obtenerReporte(estrellas: Int, mensajes: List<String>): String {
    return mensajes.getOrElse(estrellas.coerceIn(1, 5) - 1) { "Sin evaluación." }
}
