package com.falconteam.bapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ParentEntity(
    @SerialName(value = "id") val id: Int? = null,
    @SerialName(value = "nombre") val name: String?,
    @SerialName(value = "apellido") val lastname: String?,
    @SerialName(value = "id_usuario") val userId: String? = null,
)