package com.falconteam.bapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.models.Evidencia
import com.falconteam.bapp.ui.viewmodel.state.GaleriaUiState
import com.falconteam.bapp.utils.SupabaseUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GaleriaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GaleriaUiState())
    val uiState: StateFlow<GaleriaUiState> = _uiState

    fun loadEvidencias() {
        viewModelScope.launch {
            _uiState.value = GaleriaUiState(isLoading = true)
            try {
                val evidencias = SupabaseUtils.postgrest
                    .from("evidencias")
                    .select()
                    .decodeList<Evidencia>()
                _uiState.value = GaleriaUiState(evidencias = evidencias)
            } catch (e: Exception) {
                _uiState.value = GaleriaUiState(error = e.message)
            }
        }
    }

    fun uploadEvidencia(evidencia: Evidencia, fileBytes: ByteArray) {
        viewModelScope.launch {
            try {
                val fileName = evidencia.archivoUrl
                SupabaseUtils.storage
                    .from("evidencias")
                    .upload(fileName, fileBytes)
                SupabaseUtils.postgrest
                    .from("evidencias")
                    .insert(evidencia)
                loadEvidencias()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
