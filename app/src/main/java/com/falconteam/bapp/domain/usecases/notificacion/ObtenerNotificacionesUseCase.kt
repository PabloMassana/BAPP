package com.falconteam.bapp.domain.usecases.notificacion

import com.falconteam.bapp.data.models.Notificacion
import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerNotificacionesUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(usuarioId: String): List<Notificacion> {
        return repository.obtenerNotificaciones(usuarioId)
    }
}
