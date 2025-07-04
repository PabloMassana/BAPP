package com.falconteam.bapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskEntity(
    @SerialName("id") val id: Long? = null,
    @SerialName("created_at") val createdAt: Long = System.currentTimeMillis(), // En formato timestamp
    @SerialName("titulo") val titulo: String,
    @SerialName("objetivo") val objetivo: String,
    @SerialName("curso_id") val cursoId: Long,
    @SerialName("profesor_id") val profesorId: Long
)
