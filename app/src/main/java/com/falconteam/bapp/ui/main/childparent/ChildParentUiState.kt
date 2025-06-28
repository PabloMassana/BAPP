package com.falconteam.bapp.ui.main.childparent

import com.falconteam.bapp.data.models.Alumno
import com.falconteam.bapp.data.models.Reporte
import com.falconteam.bapp.data.models.Task

data class ChildParentUiState(
    val alumno: Alumno = Alumno("", "", ""),
    val reportes: List<Reporte> = emptyList(),
    val tareasPendientes: List<Task> = emptyList(),
    val tareasCompletadas: List<Task> = emptyList()
)

