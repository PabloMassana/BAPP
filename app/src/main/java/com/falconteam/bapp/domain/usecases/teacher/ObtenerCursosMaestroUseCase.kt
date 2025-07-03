package com.falconteam.bapp.domain.usecases.teacher

import com.falconteam.bapp.data.models.Curso
import com.falconteam.bapp.data.repository.TeacherRepository

class ObtenerCursosMaestroUseCase(private val mainRepository: TeacherRepository) {
    suspend operator fun invoke(idMaestro: String): List<Curso> {
        return mainRepository.obtenerCursosPorMaestro(idMaestro)
    }
}
