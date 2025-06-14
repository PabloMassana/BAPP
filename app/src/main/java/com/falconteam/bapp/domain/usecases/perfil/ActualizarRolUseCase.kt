package com.falconteam.bapp.domain.usecases.perfil

import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ActualizarRolUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(userId: String, nuevoRol: String) {
        repository.actualizarRol(userId, nuevoRol)
    }
}
