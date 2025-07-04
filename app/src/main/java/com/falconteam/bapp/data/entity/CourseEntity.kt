package com.falconteam.bapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseEntity(
    @SerialName(value = "id") val id: Int? = null,
    @SerialName(value = "nivel") val level: String?,
    @SerialName(value = "id_docente") val idTeacher: Int? = null,
    @SerialName(value = "grupo") val courseSection: String?,
)