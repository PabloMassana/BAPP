package com.falconteam.bapp.ui.main.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.models.Bitacora
import com.falconteam.bapp.domain.usecases.bitacora.AgregarBitacoraUseCase
import com.falconteam.bapp.domain.usecases.bitacora.ObtenerBitacorasUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BitacoraViewModel(
    private val obtenerBitacorasUseCase: ObtenerBitacorasUseCase,
    private val agregarBitacoraUseCase: AgregarBitacoraUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BitacoraUiState())
    val uiState: StateFlow<BitacoraUiState> = _uiState

    fun cargarBitacoras(alumnoId: String) {
        viewModelScope.launch {
            _uiState.value = BitacoraUiState(isLoading = true)
            try {
                val bitacoras = obtenerBitacorasUseCase(alumnoId)
                _uiState.value = BitacoraUiState(bitacoras = bitacoras)
            } catch (e: Exception) {
                _uiState.value = BitacoraUiState(error = e.message)
            }
        }
    }

    fun agregarBitacora(bitacora: Bitacora) {
        viewModelScope.launch {
            try {
                agregarBitacoraUseCase(bitacora)
                cargarBitacoras(bitacora.idAlumno)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
