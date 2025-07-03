package com.falconteam.bapp.domain.usecases.teacher

import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.data.repository.TeacherRepository

class ObtenerActividadesPorMaestroUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(maestroId: String): List<Actividad> {
        return repository.obtenerActividadesPorMaestro(maestroId)
    }
}
