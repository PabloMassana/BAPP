package com.falconteam.bapp.ui.main.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.usecases.notificacion.MarcarNotificacionComoLeidaUseCase
import com.falconteam.bapp.domain.usecases.notificacion.ObtenerNotificacionesUseCase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotificacionViewModel(
    private val obtenerNotificacionesUseCase: ObtenerNotificacionesUseCase,
    private val marcarLeidaUseCase: MarcarNotificacionComoLeidaUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotificacionUiState())
    val uiState: StateFlow<NotificacionUiState> = _uiState

    fun cargarNotificaciones(usuarioId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val lista = obtenerNotificacionesUseCase(usuarioId)
                _uiState.value = _uiState.value.copy(notificaciones = lista, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message, isLoading = false)
            }
        }
    }

    fun marcarComoLeida(notificacionId: String) {
        viewModelScope.launch {
            try {
                marcarLeidaUseCase(notificacionId)
                val actualizadas = _uiState.value.notificaciones.map {
                    if (it.id == notificacionId) it.copy(leida = true) else it
                }
                _uiState.value = _uiState.value.copy(notificaciones = actualizadas)
            } catch (_: Exception) {
                // Ignorar error silenciosamente
            }
        }
    }
}

