package com.falconteam.bapp.domain.usecases.teacher

import com.falconteam.bapp.data.repository.TeacherRepository
import com.falconteam.bapp.domain.models.ActividadModel

class ObtenerActividadesPorMaestroUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(maestroId: String): List<ActividadModel> {
        return repository.obtenerActividadesPorMaestro(maestroId)
    }
}
