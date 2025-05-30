package com.falconteam.bapp.viewmodel

import androidx.lifecycle.ViewModel
import com.falconteam.bapp.viewmodel.state.PerfilUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PerfilViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PerfilUiState())
    val uiState: StateFlow<PerfilUiState> = _uiState

    fun actualizarPerfil(nombre: String, email: String) {
        _uiState.value = _uiState.value.copy(nombre = nombre, email = email)
    }
}