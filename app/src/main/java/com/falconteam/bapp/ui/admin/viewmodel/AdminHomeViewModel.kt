package com.falconteam.bapp.ui.admin.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Grupo(val nombre: String)
data class Actividad(val titulo: String, val hora: String)

class AdminHomeViewModel : ViewModel() {

    private val _grupos = MutableStateFlow(
        listOf(
            Grupo("Kinder 4 Sección A"),
            Grupo("Kinder 4 Sección B"),
            Grupo("Kinder 5 Sección A"),
            Grupo("Kinder 5 Sección B"),
            Grupo("Preparatoria Sección A"),
            Grupo("Preparatoria Sección B")
        )
    )
    val grupos: StateFlow<List<Grupo>> = _grupos

    private val _actividades = MutableStateFlow(
        listOf(
            Actividad("Dibujar y colorear", "08:00 AM"),
            Actividad("Canciones educativas", "09:30 AM"),
            Actividad("Lectura guiada", "11:00 AM")
        )
    )
    val actividades: StateFlow<List<Actividad>> = _actividades
}
