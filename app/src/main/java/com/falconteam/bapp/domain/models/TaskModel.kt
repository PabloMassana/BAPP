package com.falconteam.bapp.domain.models

import com.falconteam.bapp.data.entity.TaskEntity
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

data class TaskModel(
    val id: Long,
    val createdAt: Date,
    val titulo: String,
    val objetivo: String,
    val completada: Boolean = false,
    val cursoId: Long,
    val profesorId: Long
) {
    companion object {
        fun fromEntity(entity: TaskEntity): TaskModel {
            return TaskModel(
                id = entity.id ?: 0L,
                createdAt = Date(entity.createdAt),
                titulo = entity.titulo,
                objetivo = entity.objetivo,
                completada = false, // Esto dependerá de la lógica de tu aplicación
                cursoId = entity.cursoId,
                profesorId = entity.profesorId
            )
        }
    }

    fun toEntity(): TaskEntity {
        return TaskEntity(
            id = id,
            createdAt = createdAt.time,
            titulo = titulo,
            objetivo = objetivo,
            cursoId = cursoId,
            profesorId = profesorId
        )
    }
}
