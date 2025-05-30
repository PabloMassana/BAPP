package com.falconteam.bapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.usecases.ObtenerEvidenciasUseCase
import com.falconteam.bapp.viewmodel.state.GaleriaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GaleriaViewModel(private val obtenerEvidenciasUseCase: ObtenerEvidenciasUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(GaleriaUiState())
    val uiState: StateFlow<GaleriaUiState> = _uiState

    fun cargarEvidencias(usuarioId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = obtenerEvidenciasUseCase(usuarioId)
            _uiState.value = result.fold(
                onSuccess = { GaleriaUiState(evidencias = it) },
                onFailure = { GaleriaUiState(error = it.message ?: "Error al cargar evidencias") }
            )
        }
    }
}