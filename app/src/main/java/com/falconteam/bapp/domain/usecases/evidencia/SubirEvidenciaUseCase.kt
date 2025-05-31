package com.falconteam.bapp.domain.usecases.evidencia

import com.falconteam.bapp.data.models.Evidencia
import com.falconteam.bapp.data.repository.SupabaseRepository

class SubirEvidenciaUseCase(private val repository: SupabaseRepository) {
    suspend operator fun invoke(evidencia: Evidencia) {
        repository.subirEvidencia(evidencia)
    }
}
