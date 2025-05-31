package com.falconteam.bapp.domain.usecases.chat

import com.falconteam.bapp.data.models.Mensaje
import com.falconteam.bapp.data.repository.SupabaseRepository

class EnviarMensajeChatUseCase(private val repository: SupabaseRepository) {
    suspend operator fun invoke(mensaje: Mensaje) {
        repository.enviarMensaje(mensaje)
    }
}
