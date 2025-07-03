package com.falconteam.bapp.domain.usecases.teacher

import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.data.repository.TeacherRepository

class AgregarActividadUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(actividad: Actividad, fileBytes: ByteArray): Actividad {
        return repository.agregarActividad(actividad, fileBytes)
    }
}
