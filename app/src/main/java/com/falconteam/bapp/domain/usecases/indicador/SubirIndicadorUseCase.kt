package com.falconteam.bapp.domain.usecases.indicador

import com.falconteam.bapp.data.models.Indicador
import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SubirIndicadorUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(indicador: Indicador) {
        repository.subirIndicador(indicador)
    }
}
