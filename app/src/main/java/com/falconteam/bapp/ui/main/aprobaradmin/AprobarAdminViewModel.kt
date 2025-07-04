package com.falconteam.bapp.ui.main.aprobaradmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.models.toDomainModel
import com.falconteam.bapp.domain.usecases.admin.AprobarDocenteUseCase
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoPadresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AprobarAdminViewModel(
    private val obtenerListadoPadresUseCase: ObtenerListadoPadresUseCase,
    private val aprobarDocenteUseCase: AprobarDocenteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AprobarAdminUiState())
    val uiState: StateFlow<AprobarAdminUiState> = _uiState.asStateFlow()

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun onStart() {
        viewModelScope.launch {
            obtenerListadoPadresUseCase().onSuccess { parents ->
                _uiState.update { state ->
                    state.copy(
                        parentsList = parents.map { it.toDomainModel() }
                    )
                }
            }.onFailure { error ->
                // Handle error, e.g., show a message to the user
                error.printStackTrace()
            }
        }
    }

    fun approveTeacher(userId: String, parentId: Int) {
        viewModelScope.launch {
            aprobarDocenteUseCase(userId, parentId).onSuccess {
                onStart() // Refresh the list after approval
            }.onFailure { error ->
                // Handle error, e.g., show a message to the user
                error.printStackTrace()
            }
        }
    }
}