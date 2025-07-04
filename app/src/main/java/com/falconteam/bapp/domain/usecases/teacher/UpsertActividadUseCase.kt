package com.falconteam.bapp.domain.usecases.teacher

import com.falconteam.bapp.data.repository.TeacherRepository
import com.falconteam.bapp.domain.models.ActividadModel

class UpsertActividadUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(actividad: ActividadModel) {
        repository.upsertActividad(actividad)
    }
}
