package com.falconteam.bapp.data.models

data class Reporte(
    val fecha: String,
    val detalles: List<DetalleReporte>,
    val comentarioDocente: String? = null
)

data class DetalleReporte(
    val titulo: String,
    val estrellas: Int // valores de 1 a 5
)
