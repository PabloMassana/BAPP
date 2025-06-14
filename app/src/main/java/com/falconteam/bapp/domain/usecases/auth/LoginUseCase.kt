package com.falconteam.bapp.domain.usecases.auth

import com.falconteam.bapp.data.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.Result

class LoginUseCase: KoinComponent {
    private val authRepository: AuthRepository by inject()

    suspend operator fun invoke(email: String, password: String): Result<Boolean> {
        return try {
            authRepository.login(email, password)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
