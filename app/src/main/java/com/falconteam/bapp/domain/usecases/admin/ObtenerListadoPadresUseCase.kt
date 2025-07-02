package com.falconteam.bapp.domain.usecases.admin

import com.falconteam.bapp.data.repository.AdminRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerListadoPadresUseCase: KoinComponent {
    private val repository by inject<AdminRepository>()

    suspend operator fun invoke() = repository.getAllParents()
}