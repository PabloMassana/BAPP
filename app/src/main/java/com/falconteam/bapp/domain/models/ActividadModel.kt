package com.falconteam.bapp.domain.models

import com.falconteam.bapp.data.entity.ActividadEntity
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

data class ActividadModel(
    val id: Int,
    val fecha: Date,
    val titulo: String,
    val hora: Time,
    val imagenUrl: String,
    val cursoId: Int
) {
    companion object {
        fun fromEntity(entity: ActividadEntity): ActividadModel {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

            return ActividadModel(
                id = entity.id ?: 0,
                fecha = dateFormat.parse(entity.fecha) ?: Date(),
                titulo = entity.titulo,
                hora = Time(timeFormat.parse(entity.hora)?.time ?: 0L),
                imagenUrl = entity.imagenUrl,
                cursoId = entity.cursoId
            )
        }
    }

    fun toEntity(): ActividadEntity {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

        return ActividadEntity(
            id = id,
            fecha = dateFormat.format(fecha),
            titulo = titulo,
            hora = timeFormat.format(hora),
            imagenUrl = imagenUrl,
            cursoId = cursoId
        )
    }
}
