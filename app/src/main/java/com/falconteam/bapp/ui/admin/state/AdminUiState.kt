package com.falconteam.bapp.ui.admin.state

import com.falconteam.bapp.data.models.Curso

data class AdminUiState(
    val cursos: List<Curso> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val pendingTeachers: Any
)