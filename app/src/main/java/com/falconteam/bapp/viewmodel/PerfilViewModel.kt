package com.falconteam.bapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.models.Usuario
import com.falconteam.bapp.ui.viewmodel.state.PerfilUiState
import com.falconteam.bapp.utils.SupabaseUtils
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUiState())
    val uiState: StateFlow<PerfilUiState> = _uiState

    fun cargarPerfil(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            try {
                val usuario = SupabaseUtils.postgrest
                    .from("usuarios")
                    .select()
                    .eq("id", userId)
                    .single()
                    .decode<Usuario>()

                _uiState.value = _uiState.value.copy(
                    usuario = usuario,
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

    fun actualizarRol(userId: String, nuevoRol: String) {
        viewModelScope.launch {
            try {
                SupabaseUtils.postgrest
                    .from("usuarios")
                    .update(mapOf("rol" to nuevoRol)) {
                        eq("id", userId)
                    }

                val usuarioActualizado = _uiState.value.usuario?.copy(rol = nuevoRol)
                _uiState.value = _uiState.value.copy(usuario = usuarioActualizado)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.localizedMessage)
            }
        }
    }

    fun cerrarSesion() {
        viewModelScope.launch {
            try {
                SupabaseUtils.auth.signOut()
                _uiState.value = PerfilUiState() // Reinicia el estado
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.localizedMessage)
            }
        }
    }
}
