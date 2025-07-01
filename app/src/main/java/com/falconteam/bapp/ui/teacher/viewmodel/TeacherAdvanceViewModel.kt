package com.falconteam.bapp.ui.teacher.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TeacherAdvanceViewModel : ViewModel() {

    private val grupo = listOf(
        "Kinder 4 Sección A",
        "Kinder 4 Sección B",
        "Kinder 5 Sección A",
        "Kinder 5 Sección B",
        "Preparatoria Sección A",
        "Preparatoria Sección B"
    )

    private val _grupoSeleccionado = MutableStateFlow(grupo.first())
    val grupoSeleccionado: StateFlow<String> = _grupoSeleccionado

    private val _busqueda = MutableStateFlow("")
    val busqueda: StateFlow<String> = _busqueda

    val grupos: List<String> = grupo

    fun seleccionarGrupo(nuevoGrupo: String) {
        _grupoSeleccionado.value = nuevoGrupo
    }

    fun actualizarBusqueda(texto: String) {
        _busqueda.value = texto
    }
}