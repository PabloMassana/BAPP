package com.falconteam.bapp.domain.usecases.alumno

import com.falconteam.bapp.data.models.Alumno
import com.falconteam.bapp.data.repository.MainRepository

class ObtenerAlumnoUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(): Alumno {
        return repository.obtenerAlumnoActual()
    }
}
