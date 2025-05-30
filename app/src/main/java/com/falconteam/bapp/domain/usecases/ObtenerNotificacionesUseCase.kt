package com.falconteam.bapp.domain.usecases

import com.falconteam.bapp.data.models.Notificacion
import com.falconteam.bapp.data.repository.SupabaseRepository

class ObtenerNotificacionesUseCase(
    private val repository: SupabaseRepository
) {
    suspend operator fun invoke(): List<Notificacion> {
        return repository.obtenerNotificaciones()
    }
}
