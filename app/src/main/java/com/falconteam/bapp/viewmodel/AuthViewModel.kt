package com.falconteam.bapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.falconteam.bapp.domain.usecases.AuthUseCase

class AuthViewModel(private val authUseCase: AuthUseCase) : ViewModel() {
    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            authUseCase.login(email, password).fold(
                onSuccess = { onSuccess() },
                onFailure = { onError(it.message ?: "Error desconocido") }
            )
        }
    }

    fun register(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            authUseCase.register(email, password).fold(
                onSuccess = { onSuccess() },
                onFailure = { onError(it.message ?: "Error desconocido") }
            )
        }
    }
}