package com.falconteam.bapp.domain.usecases.auth

import com.falconteam.bapp.data.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpUseCase: KoinComponent {

    private val authRepository by inject<AuthRepository>()

    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return try {
            authRepository.signUp(email, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
