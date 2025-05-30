package com.falconteam.bapp.domain.usecases

import com.falconteam.bapp.data.models.Evidencia
import com.falconteam.bapp.data.repository.SupabaseRepository

class ObtenerEvidenciasUseCase(
    private val repository: SupabaseRepository
) {
    suspend operator fun invoke(alumnoId: String): List<Evidencia> {
        return repository.obtenerEvidenciasPorAlumno(alumnoId)
    }
}
