package com.falconteam.bapp.domain.usecases.teacher

import com.falconteam.bapp.data.entity.main.TeacherEntity
import com.falconteam.bapp.data.repository.TeacherRepository

class ObtenerMaestroUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(): TeacherEntity {
        return repository.obtenerMaestroActual()
    }
}
