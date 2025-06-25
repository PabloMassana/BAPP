package com.falconteam.bapp.domain.usecases.alumno

import com.falconteam.bapp.data.models.Reporte
import com.falconteam.bapp.data.repository.MainRepository

class ObtenerUltimoReporteUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(alumnoId: String): Reporte? {
        return repository.obtenerUltimoReportePorAlumno(alumnoId)
    }
}