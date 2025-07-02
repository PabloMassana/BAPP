package com.falconteam.bapp.domain.usecases.auth

import com.falconteam.bapp.data.repository.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LogoutUseCase: KoinComponent {
    private val repository by inject<AuthRepository>()

    suspend operator fun invoke(): Result<Boolean> = repository.logout()
}