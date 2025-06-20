package com.falconteam.bapp.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val id: String? = null,
    val username: String?,
    val email: String,
    val rol: String,
    val created_at: String? = null
)
