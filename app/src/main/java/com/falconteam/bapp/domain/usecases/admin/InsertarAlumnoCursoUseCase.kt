package com.falconteam.bapp.domain.usecases.admin

import com.falconteam.bapp.data.repository.AdminRepository
import com.falconteam.bapp.domain.models.StudentModel
import com.falconteam.bapp.domain.models.toEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InsertarAlumnoCursoUseCase : KoinComponent {
    private val adminRepository by inject<AdminRepository>()

    suspend operator fun invoke(student: StudentModel, idCourse: Int) =
        adminRepository.insertStudentWithCourse(student.toEntity(), idCourse)

}