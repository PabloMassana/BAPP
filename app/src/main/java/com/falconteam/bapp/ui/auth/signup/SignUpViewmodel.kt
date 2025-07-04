package com.falconteam.bapp.ui.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.local.DataStoreHelper
import com.falconteam.bapp.domain.usecases.auth.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpScreenUiState())
    val uiState: StateFlow<SignUpScreenUiState> = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun signUpAction() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            signUpUseCase(
                email = _uiState.value.email,
                password = _uiState.value.password,
                username = _uiState.value.username // Pasa el username
            ).onSuccess { user ->
                _uiState.update {
                    it.copy(
                        signUpSuccessful = true,
                        isLoading = false,
                        idUsuario = user?.id.toString()
                    )
                }
            }.onFailure { e ->
                _uiState.update { it.copy(errorMessage = e.message, isLoading = false) }
            }
        }
    }
}
