package com.falconteam.bapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentEntity(
    @SerialName(value = "id") val id: Int? = null,
    @SerialName(value = "nombre") val name: String?,
    @SerialName(value = "apellido") val lastName: String?,
    @SerialName(value = "fecha_nacimiento") val birthDate: String? = null,
)
