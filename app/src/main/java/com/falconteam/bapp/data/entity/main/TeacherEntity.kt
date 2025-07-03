package com.falconteam.bapp.data.entity.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeacherEntity(
    @SerialName(value = "id") val id: Int? = null,
    @SerialName(value = "nombre") val name: String?,
    @SerialName(value = "apellido") val lastname: String?,
    @SerialName(value = "id_usuario") val userId: String?,
)