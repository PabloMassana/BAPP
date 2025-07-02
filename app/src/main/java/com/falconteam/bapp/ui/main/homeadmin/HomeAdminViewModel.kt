package com.falconteam.bapp.ui.main.homeadmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.models.toDomainModel
import com.falconteam.bapp.domain.models.toListSectionItem
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoPadresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeAdminViewModel(
    private val obtenerListadoPadresUseCase: ObtenerListadoPadresUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeAdminUiState())
    val uiState: StateFlow<HomeAdminUiState> = _uiState.asStateFlow()

    fun getParentsList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            obtenerListadoPadresUseCase().onSuccess { parents ->
                val parentsList = parents.map { it.toDomainModel() }
                _uiState.update {
                    it.copy(
                        parentsList = parentsList.toListSectionItem(),
                        isLoading = false
                    )
                }
            }.onFailure { _ ->
                _uiState.update { it.copy(parentsList = emptyList(), isLoading = false) }
            }
        }
    }
}