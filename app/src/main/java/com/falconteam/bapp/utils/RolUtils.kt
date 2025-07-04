package com.falconteam.bapp.utils

enum class UserRole {
    ADMIN,
    TEACHER,
    PARENT;

    companion object {
        fun getRoleFromString(role: String): UserRole {
            return when (role) {
                "ADMIN" -> ADMIN
                "TEACHER" -> TEACHER
                "PARENT" -> PARENT
                else -> PARENT
            }
        }
    }
}
