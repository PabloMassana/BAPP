
package com.falconteam.bapp.ui.main.homeadmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.models.toDomainModel
import com.falconteam.bapp.domain.models.toListSectionItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeAdminViewModel(
    private val obtenerListadoPadresUseCase: ObtenerListadoPadresUseCase
) : ViewModel() {

    private val _adminUiState = MutableStateFlow(HomeAdminUiState())
    val uiState: StateFlow<HomeAdminUiState> = _adminUiState.asStateFlow()

    fun getParentsList() {
        viewModelScope.launch {
            _adminUiState.update { it.copy(isLoading = true) }

            obtenerListadoPadresUseCase().onSuccess { parents ->
                val domainList = parents.map { it.toDomainModel() }

                _adminUiState.update {
                    it.copy(
                        parentsList = domainList.toListSectionItem(),
                        isLoading = false
                    )
                }
            }.onFailure { error ->
                // Si falla la petición, mostramos lista vacía
                _adminUiState.update {
                    it.copy(parentsList = emptyList(), isLoading = false)
                }
            }
        }
    }
}
