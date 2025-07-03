package com.falconteam.bapp.domain.usecases.perfil

import com.falconteam.bapp.data.entity.main.UserEntity
import com.falconteam.bapp.data.models.Usuario
import com.falconteam.bapp.data.repository.MainRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerPerfilUseCase : KoinComponent {
    private val repository: MainRepository by inject()

    suspend operator fun invoke(userId: String): UserEntity {
        return repository.obtenerUsuario(userId)
    }
}
