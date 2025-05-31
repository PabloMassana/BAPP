package com.falconteam.bapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.model.Notificacion
import com.falconteam.bapp.ui.viewmodel.state.NotificacionUiState
import com.falconteam.bapp.utils.SupabaseUtils
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotificacionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NotificacionUiState())
    val uiState: StateFlow<NotificacionUiState> = _uiState

    fun cargarNotificaciones(usuarioId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val response = SupabaseUtils.postgrest
                    .from("notificaciones")
                    .select()
                    .eq("destinatario_id", usuarioId)
                    .order("fecha", ascending = false)
                    .decodeList<Notificacion>()

                _uiState.value = _uiState.value.copy(
                    notificaciones = response,
                    isLoading = false,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.localizedMessage,
                    isLoading = false
                )
            }
        }
    }

    fun marcarComoLeida(notificacionId: String) {
        viewModelScope.launch {
            try {
                SupabaseUtils.postgrest
                    .from("notificaciones")
                    .update(mapOf("leida" to true)) {
                        eq("id", notificacionId)
                    }

                val listaActualizada = _uiState.value.notificaciones.map {
                    if (it.id == notificacionId) it.copy(leida = true) else it
                }

                _uiState.value = _uiState.value.copy(notificaciones = listaActualizada)
            } catch (_: Exception) {
                // No actualizamos el estado si falla silenciosamente
            }
        }
    }
}
