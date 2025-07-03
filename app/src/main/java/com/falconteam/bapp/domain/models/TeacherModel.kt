package com.falconteam.bapp.domain.models
import com.falconteam.bapp.data.entity.TeacherEntity

data class TeacherModel(
    val id: Int,
    val name: String,
    val lastname: String,
    val userId: String
)

fun TeacherEntity.toDomainModel() = TeacherModel(
    id = id ?: 0,
    name = name.orEmpty(),
    lastname = lastname.orEmpty(),
    userId = userId.orEmpty()
)
