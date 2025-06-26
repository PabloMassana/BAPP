package com.falconteam.bapp.ui.admin.state

data class Teacher(
    val id: String,
    val name: String,
    val imageRes: Int // example: R.drawable.avatar_placeholder
)

data class ApproveTeachersState(
    val teachers: List<Teacher> = emptyList(),
    val searchQuery: String = ""
)
