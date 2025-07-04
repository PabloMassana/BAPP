package com.falconteam.bapp.ui.main.teacheractivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.models.ActividadModel
import com.falconteam.bapp.domain.usecases.teacher.ObtenerActividadesPorMaestroUseCase
import com.falconteam.bapp.domain.usecases.teacher.ObtenerMaestroUseCase
import com.falconteam.bapp.domain.usecases.teacher.UpsertActividadUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeacherActivitiesViewModel(
    private val obtenerMaestroUseCase: ObtenerMaestroUseCase,
    private val obtenerActividadesPorMaestroUseCase: ObtenerActividadesPorMaestroUseCase,
    private val upsertActividadUseCase: UpsertActividadUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TeacherActivitiesUiState())
    val uiState: StateFlow<TeacherActivitiesUiState> = _uiState.asStateFlow()

    init {
        cargarActividades()
    }

    fun cargarActividades() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            try {
                val maestro = obtenerMaestroUseCase() // Obtener el maestro
                val actividades = obtenerActividadesPorMaestroUseCase(maestro.id.toString())
                _uiState.value = TeacherActivitiesUiState(actividades = actividades)
            } catch (e: Exception) {
                _uiState.value = TeacherActivitiesUiState(error = e.message)
            }
        }
    }

    fun guardarActividad(model: ActividadModel) {
        viewModelScope.launch {
            try {
                upsertActividadUseCase(model)
                cargarActividades()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
