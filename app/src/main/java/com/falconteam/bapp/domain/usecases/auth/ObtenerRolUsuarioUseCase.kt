package com.falconteam.bapp.domain.usecases.auth

import com.falconteam.bapp.data.models.Rol
import com.falconteam.bapp.data.repository.MainRepository

class ObtenerRolUsuarioUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(userId: String): Rol? {
        return repository.obtenerRolUsuario(userId)
    }
}
