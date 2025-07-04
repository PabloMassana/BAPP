package com.falconteam.bapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActividadEntity(
    @SerialName("id") val id: Int? = null,
    @SerialName("fecha") val fecha: String, // Formato "yyyy-MM-dd"
    @SerialName("nombre") val titulo: String,
    @SerialName("hora") val hora: String,   // Formato "HH:mm:ss"
    @SerialName("directorio_img") val imagenUrl: String,
    @SerialName("curso_id") val cursoId: Int
)