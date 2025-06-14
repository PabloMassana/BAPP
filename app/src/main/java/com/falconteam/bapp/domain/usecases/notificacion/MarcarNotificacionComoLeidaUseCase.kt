package com.falconteam.bapp.domain.usecases.notificacion

import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MarcarNotificacionComoLeidaUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(notificacionId: String) {
        repository.marcarNotificacionComoLeida(notificacionId)
    }
}
