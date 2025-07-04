package com.falconteam.bapp.data.models

data class Task(
    val id: Long,
    val titulo: String,
    val objetivo: String,
    val completada: Boolean = false
)
