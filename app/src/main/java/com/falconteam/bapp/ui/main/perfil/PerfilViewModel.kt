package com.falconteam.bapp.ui.main.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.models.Rol
import com.falconteam.bapp.data.models.Usuario
import com.falconteam.bapp.domain.usecases.perfil.ActualizarRolUseCase
import com.falconteam.bapp.domain.usecases.perfil.CerrarSesionUseCase
import com.falconteam.bapp.domain.usecases.perfil.ObtenerPerfilUseCase
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val obtenerPerfilUseCase: ObtenerPerfilUseCase,
    private val actualizarRolUseCase: ActualizarRolUseCase,
    private val cerrarSesionUseCase: CerrarSesionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PerfilUiState())
    val uiState: StateFlow<PerfilUiState> = _uiState

    fun cargarPerfil(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val usuario = obtenerPerfilUseCase(userId)
                _uiState.value = _uiState.value.copy(usuario = usuario, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.localizedMessage, isLoading = false)
            }
        }
    }

    fun actualizarRol(userId: String, nuevoRol: String) {
        viewModelScope.launch {
            try {
                actualizarRolUseCase(userId, nuevoRol)
                val actualizado = _uiState.value.usuario?.copy(rol = Rol.valueOf(nuevoRol))
                _uiState.value = _uiState.value.copy(usuario = actualizado)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.localizedMessage)
            }
        }
    }

    fun cerrarSesion() {
        viewModelScope.launch {
            try {
                cerrarSesionUseCase()
                _uiState.value = PerfilUiState()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.localizedMessage)
            }
        }
    }
}