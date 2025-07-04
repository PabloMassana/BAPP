package com.falconteam.bapp.ui.main.borraradmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.models.toDomainModel
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoCursosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeleteAdminViewModel(
    private val obtenerListadoCursosUseCase: ObtenerListadoCursosUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(DeleteAdminUiState())
    val uiState: StateFlow<DeleteAdminUiState> = _uiState.asStateFlow()

    init {
        onStart()
    }

    fun onDropdownExpanded(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(expandedDropdown = value)
        }
    }

    private fun onStart() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            obtenerListadoCursosUseCase().onSuccess {
                _uiState.update { currentState ->
                    currentState.copy(
                        courses = it.map { course -> course.toDomainModel() },
                        isLoading = false
                    )
                }
            }.onFailure {
                _uiState.update { currentState ->
                    currentState.copy(isLoading = false)
                }
            }
        }
    }
}