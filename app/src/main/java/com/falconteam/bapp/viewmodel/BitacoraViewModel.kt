package com.falconteam.bapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.usecases.ObtenerBitacorasUseCase
import com.falconteam.bapp.domain.usecases.AgregarBitacoraUseCase
import com.falconteam.bapp.viewmodel.state.BitacoraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BitacoraViewModel(
    private val obtenerBitacorasUseCase: ObtenerBitacorasUseCase,
    private val agregarBitacoraUseCase: AgregarBitacoraUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BitacoraUiState())
    val uiState: StateFlow<BitacoraUiState> = _uiState

    fun cargarBitacoras(usuarioId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = obtenerBitacorasUseCase(usuarioId)
            _uiState.value = result.fold(
                onSuccess = { BitacoraUiState(bitacoras = it) },
                onFailure = { BitacoraUiState(error = it.message ?: "Error desconocido") }
            )
        }
    }

    fun agregarBitacora(usuarioId: String, contenido: String) {
        viewModelScope.launch {
            agregarBitacoraUseCase(usuarioId, contenido).fold(
                onSuccess = { cargarBitacoras(usuarioId) },
                onFailure = { _uiState.value = _uiState.value.copy(error = it.message ?: "Error al agregar") }
            )
        }
    }
}