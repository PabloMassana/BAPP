package com.falconteam.bapp.domain.usecases.alumno

import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.data.repository.MainRepository

class ObtenerActividadesUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(alumnoId: String): List<Actividad> {
        return repository.obtenerActividadesPorAlumno(alumnoId)
    }
}
