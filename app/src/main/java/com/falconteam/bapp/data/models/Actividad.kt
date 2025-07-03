package com.falconteam.bapp.data.models

import java.sql.Time
import java.util.Date

data class Actividad(
    val id: Int,
    val fecha: Date,
    val titulo: String,
    val hora: Time,
    val imagenUrl: String,
    val cursoId: Int
)