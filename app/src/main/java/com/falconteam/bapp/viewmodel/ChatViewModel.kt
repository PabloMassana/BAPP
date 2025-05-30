package com.falconteam.bapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.usecases.EnviarMensajeChatUseCase
import com.falconteam.bapp.viewmodel.state.ChatUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(private val enviarMensajeChatUseCase: EnviarMensajeChatUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState

    fun enviarMensaje(usuarioId: String, mensaje: String) {
        viewModelScope.launch {
            enviarMensajeChatUseCase(usuarioId, mensaje).fold(
                onSuccess = { _uiState.value = _uiState.value.copy(mensajeEnviado = true) },
                onFailure = { _uiState.value = _uiState.value.copy(error = it.message ?: "Error al enviar") }
            )
        }
    }
}