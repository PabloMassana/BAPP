package com.falconteam.bapp.domain.usecases.auth

import com.falconteam.bapp.data.models.Usuario
import com.falconteam.bapp.data.repository.SupabaseRepository

class IniciarSesionUseCase(private val repository: SupabaseRepository) {
    suspend operator fun invoke(email: String, password: String): Usuario? {
        return repository.iniciarSesion(email, password)
    }
}
