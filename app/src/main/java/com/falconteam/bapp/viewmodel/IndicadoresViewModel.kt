package com.falconteam.bapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.usecases.ObtenerIndicadoresUseCase
import com.falconteam.bapp.viewmodel.state.IndicadoresUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IndicadoresViewModel(private val obtenerIndicadoresUseCase: ObtenerIndicadoresUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(IndicadoresUiState())
    val uiState: StateFlow<IndicadoresUiState> = _uiState

    fun cargarIndicadores(usuarioId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = obtenerIndicadoresUseCase(usuarioId)
            _uiState.value = result.fold(
                onSuccess = { IndicadoresUiState(indicadores = it) },
                onFailure = { IndicadoresUiState(error = it.message ?: "Error al cargar indicadores") }
            )
        }
    }
}