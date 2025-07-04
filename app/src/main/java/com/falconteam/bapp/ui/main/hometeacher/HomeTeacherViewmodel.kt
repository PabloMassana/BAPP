package com.falconteam.bapp.ui.main.hometeacher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.usecases.teacher.ObtenerActividadesPorMaestroUseCase
import com.falconteam.bapp.domain.usecases.teacher.ObtenerCursosMaestroUseCase
import com.falconteam.bapp.domain.usecases.teacher.ObtenerMaestroUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeTeacherViewModel(
    private val obtenerActividadesPorMaestroUseCase: ObtenerActividadesPorMaestroUseCase,
    private val obtenerMaestroUseCase: ObtenerMaestroUseCase,
    private val obtenerCursosMaestroUseCase: ObtenerCursosMaestroUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeTeacherUiState())
    val uiState: StateFlow<HomeTeacherUiState> = _uiState

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true)

                val maestro = obtenerMaestroUseCase()
                val maestroId = maestro.id ?: throw IllegalStateException("ID del maestro es nulo")

                val actividades = obtenerActividadesPorMaestroUseCase(maestroId.toString())
                val cursos = obtenerCursosMaestroUseCase(maestroId.toString())

                val nombre = maestro.name.orEmpty()
                val apellido = maestro.lastname.orEmpty()
                val nombreCompleto = "$nombre $apellido".trim()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    // actividades = actividades,
                    cursos = cursos,
                    teacherName = nombreCompleto
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = when (e) {
                        is java.io.IOException -> "Error de red. Verifica tu conexión."
                        else -> e.message ?: "Ocurrió un error inesperado"
                    }
                )
            }
        }
    }
}
