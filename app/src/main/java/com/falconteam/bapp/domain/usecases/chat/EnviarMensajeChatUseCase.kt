package com.falconteam.bapp.domain.usecases.chat

import com.falconteam.bapp.data.models.Mensaje
import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EnviarMensajeChatUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(mensaje: Mensaje) {
        repository.enviarMensaje(mensaje)
    }
}
