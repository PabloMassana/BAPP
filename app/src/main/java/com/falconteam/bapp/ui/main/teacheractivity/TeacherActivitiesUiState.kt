package com.falconteam.bapp.ui.main.teacheractivity

import com.falconteam.bapp.domain.models.ActividadModel

data class TeacherActivitiesUiState(
    val loading: Boolean = false,
    val actividades: List<ActividadModel> = emptyList(),
    val error: String? = null
)

