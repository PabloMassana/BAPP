package com.falconteam.bapp.domain.usecases.admin

import com.falconteam.bapp.data.repository.AdminRepository
import com.falconteam.bapp.domain.models.CourseModel
import com.falconteam.bapp.domain.models.toEntityRequest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InsertarCursoUseCase : KoinComponent {
    private val repository by inject<AdminRepository>()

    suspend operator fun invoke(courseModel: CourseModel) =
        repository.insertCourse(courseModel.toEntityRequest())
}