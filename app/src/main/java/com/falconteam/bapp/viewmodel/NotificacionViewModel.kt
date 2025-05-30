package com.falconteam.bapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.usecases.ObtenerNotificacionesUseCase
import com.falconteam.bapp.viewmodel.state.NotificacionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotificacionViewModel(private val useCase: ObtenerNotificacionesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificacionUiState())
    val uiState: StateFlow<NotificacionUiState> = _uiState

    fun cargarNotificaciones(usuarioId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = useCase(usuarioId)
            _uiState.value = result.fold(
                onSuccess = { NotificacionUiState(notificaciones = it) },
                onFailure = { NotificacionUiState(error = it.message ?: "Error al cargar") }
            )
        }
    }
}