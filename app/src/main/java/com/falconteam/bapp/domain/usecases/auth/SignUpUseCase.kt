package com.falconteam.bapp.domain.usecases.auth

import com.falconteam.bapp.data.repository.AuthRepository
import io.github.jan.supabase.auth.user.UserInfo
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpUseCase : KoinComponent {

    private val authRepository by inject<AuthRepository>()

    suspend operator fun invoke(
        email: String,
        password: String,
        username: String
    ): Result<UserInfo?> =
        authRepository.signUp(email, password, username)

}
