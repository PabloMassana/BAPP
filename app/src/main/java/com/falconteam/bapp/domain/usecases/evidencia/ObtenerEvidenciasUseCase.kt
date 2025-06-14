package com.falconteam.bapp.domain.usecases.evidencia

import com.falconteam.bapp.data.models.Evidencia
import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerEvidenciasUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(idCurso: String): List<Evidencia> {
        return repository.obtenerEvidencias(idCurso)
    }
}
