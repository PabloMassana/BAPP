package com.falconteam.bapp.ui.main.homeparent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeParentViewModel(
    private val obtenerAlumnoUseCase: ObtenerAlumnoUseCase,
    private val obtenerActividadesUseCase: ObtenerActividadesUseCase,
    private val obtenerUltimoReporteUseCase: ObtenerUltimoReporteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeParentUiState())
    val uiState: StateFlow<HomeParentUiState> = _uiState.asStateFlow()

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val alumno = obtenerAlumnoUseCase()
                val actividades = obtenerActividadesUseCase(alumno.id)
                val reporte = obtenerUltimoReporteUseCase(alumno.id)

                _uiState.value = HomeParentUiState(
                    alumno = alumno,
                    actividades = actividades,
                    ultimoReporte = reporte,
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Ha ocurrido un error inesperado"
                )
            }
        }
    }
}
