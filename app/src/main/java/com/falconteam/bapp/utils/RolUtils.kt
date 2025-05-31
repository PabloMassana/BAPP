package com.falconteam.bapp.ui.utils

enum class UserRole {
    ADMIN,
    TEACHER,
    PARENT
}

object RolUtils {
    fun getRoleFromString(role: String): UserRole {
        return when (role.lowercase()) {
            "admin" -> UserRole.ADMIN
            "teacher" -> UserRole.TEACHER
            "parent" -> UserRole.PARENT
            else -> UserRole.PARENT
        }
    }
}
