package com.falconteam.bapp.domain.usecases.auth

import com.falconteam.bapp.data.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.Result

class LoginUseCase: KoinComponent {
    private val authRepository: AuthRepository by inject()

    suspend operator fun invoke(email: String, password: String) = authRepository.login(
        email, password
    )
}
