package com.falconteam.bapp.domain.usecases.perfil

import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CerrarSesionUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke() {
        repository.cerrarSesion()
    }
}
