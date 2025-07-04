package com.falconteam.bapp.ui.main.hometeacher

import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.data.models.Curso

data class HomeTeacherUiState(
    val isLoading: Boolean = false,
    val actividades: List<Actividad> = emptyList(),
    val cursos: List<Curso> = emptyList(),
    val teacherName: String = "",
    val errorMessage: String? = null
)
