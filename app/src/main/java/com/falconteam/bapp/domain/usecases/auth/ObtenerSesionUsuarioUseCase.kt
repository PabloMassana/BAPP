package com.falconteam.bapp.domain.usecases.auth

import com.falconteam.bapp.data.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerSesionUsuarioUseCase: KoinComponent {
    private val authRepository by inject<AuthRepository>()

    suspend operator fun invoke() = authRepository.retrieveUserSessionOrNull()
}