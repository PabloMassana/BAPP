package com.falconteam.bapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.usecases.bitacora.ObtenerBitacorasUseCase
import com.falconteam.bapp.ui.viewmodel.state.BitacoraUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BitacoraViewModel(
    private val obtenerBitacorasUseCase: ObtenerBitacorasUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BitacoraUiState())
    val uiState: StateFlow<BitacoraUiState> = _uiState

    fun cargarBitacoras() {
        viewModelScope.launch {
            _uiState.value = BitacoraUiState(isLoading = true)
            try {
                val bitacoras = obtenerBitacorasUseCase()
                _uiState.value = BitacoraUiState(bitacoras = bitacoras)
            } catch (e: Exception) {
                _uiState.value = BitacoraUiState(error = e.message)
            }
        }
    }
}
