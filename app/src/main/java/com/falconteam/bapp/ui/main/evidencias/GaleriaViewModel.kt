package com.falconteam.bapp.ui.main.evidencias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.models.Evidencia
import com.falconteam.bapp.domain.usecases.evidencia.ObtenerEvidenciasUseCase
import com.falconteam.bapp.domain.usecases.evidencia.SubirEvidenciaUseCase
import com.falconteam.bapp.ui.main.evidencias.GaleriaUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GaleriaViewModel(
    private val obtenerEvidenciasUseCase: ObtenerEvidenciasUseCase,
    private val subirEvidenciaUseCase: SubirEvidenciaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(GaleriaUiState())
    val uiState: StateFlow<GaleriaUiState> = _uiState

    fun loadEvidencias(idCurso: String) {
        viewModelScope.launch {
            _uiState.value = GaleriaUiState(isLoading = true)
            try {
                val evidencias = obtenerEvidenciasUseCase(idCurso)
                _uiState.value = GaleriaUiState(evidencias = evidencias)
            } catch (e: Exception) {
                _uiState.value = GaleriaUiState(error = e.message)
            }
        }
    }

    fun uploadEvidencia(evidencia: Evidencia, fileBytes: ByteArray) {
        viewModelScope.launch {
            try {
                subirEvidenciaUseCase(evidencia, fileBytes)
                loadEvidencias(evidencia.idCurso)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
