package com.falconteam.bapp.domain.usecases.chat

import com.falconteam.bapp.data.models.Mensaje
import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerMensajesChatUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(conversacionId: String): List<Mensaje> {
        return repository.obtenerMensajes(conversacionId)
    }
}
