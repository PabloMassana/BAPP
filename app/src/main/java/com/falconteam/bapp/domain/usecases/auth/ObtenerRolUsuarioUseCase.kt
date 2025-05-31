package com.falconteam.bapp.domain.usecases.auth

import com.falconteam.bapp.data.repository.SupabaseRepository
import com.falconteam.bapp.data.models.Rol

class ObtenerRolUsuarioUseCase(private val repository: SupabaseRepository) {
    suspend operator fun invoke(userId: String): Rol? {
        return repository.obtenerRolUsuario(userId)
    }
}
