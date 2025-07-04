package com.falconteam.bapp.ui.main.homeparent

import com.falconteam.bapp.data.models.Alumno
import com.falconteam.bapp.data.models.Reporte

data class HomeParentUiState(
    val alumno: Alumno? = null,
    val actividades: List<Actividad> = emptyList(),
    val ultimoReporte: Reporte? = null,
    val isLoading: Boolean = true,
    val error: String? = null
)
