package com.falconteam.bapp.domain.usecases.notificacion

import com.falconteam.bapp.data.models.Notificacion
import com.falconteam.bapp.data.repository.SupabaseRepository

class ObtenerNotificacionesUseCase(private val repository: SupabaseRepository) {
    suspend operator fun invoke(usuarioId: String): List<Notificacion> {
        return repository.obtenerNotificaciones(usuarioId)
    }
}
