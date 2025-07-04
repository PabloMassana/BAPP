package com.falconteam.bapp.domain.usecases.admin

import com.falconteam.bapp.data.repository.AdminRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerListadoCursosUseCase: KoinComponent {

    private val repository by inject<AdminRepository>()

    suspend operator fun invoke() = repository.getAllCourses()
}