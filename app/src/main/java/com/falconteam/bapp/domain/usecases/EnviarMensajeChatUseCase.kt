package com.falconteam.bapp.domain.usecases

import com.falconteam.bapp.data.models.MensajeChat // Suponiendo que lo definas
import com.falconteam.bapp.data.repository.SupabaseRepository

class EnviarMensajeChatUseCase(
    private val repository: SupabaseRepository
) {
    suspend operator fun invoke(mensaje: MensajeChat) {
        repository.enviarMensajeChat(mensaje) // Este método debería crearse en el repo
    }
}
