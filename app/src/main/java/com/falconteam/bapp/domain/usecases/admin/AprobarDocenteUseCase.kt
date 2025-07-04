package com.falconteam.bapp.domain.usecases.admin

import com.falconteam.bapp.data.repository.AdminRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AprobarDocenteUseCase : KoinComponent {
    private val repository by inject<AdminRepository>()

    suspend operator fun invoke(userId: String, parentId: Int) =
        repository.approveParentAsTeacher(userId, parentId)
}