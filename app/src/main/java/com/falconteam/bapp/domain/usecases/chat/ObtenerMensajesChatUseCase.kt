package com.falconteam.bapp.domain.usecases.chat

import com.falconteam.bapp.data.models.Mensaje
import com.falconteam.bapp.data.repository.SupabaseRepository

class ObtenerMensajesChatUseCase(private val repository: SupabaseRepository) {
    suspend operator fun invoke(conversacionId: String): List<Mensaje> {
        return repository.obtenerMensajes(conversacionId)
    }
}
