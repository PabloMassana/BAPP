package com.falconteam.bapp.domain.usecases.admin

import com.falconteam.bapp.data.entity.StudentEntity
import com.falconteam.bapp.data.repository.AdminRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ObtenerListadoAlumnosUseCase: KoinComponent {

    private val repository: AdminRepository by inject()

    suspend operator fun invoke(): Result<List<StudentEntity>> {
        return repository.getAllStudents()
    }
}