package com.falconteam.bapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StudentCourseEntity(
    @SerialName(value = "id") val id: Int? = null,
    @SerialName(value = "curso_id") val idCourse: Int?,
    @SerialName(value = "hijo_id") val idStudent: Int?,
)
