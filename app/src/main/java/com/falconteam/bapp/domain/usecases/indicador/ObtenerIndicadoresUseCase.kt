package com.falconteam.bapp.domain.usecases.indicador

import com.falconteam.bapp.data.models.Indicador
import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerIndicadoresUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(alumnoId: String): List<Indicador> {
        return repository.obtenerIndicadores(alumnoId)
    }
}
