package com.falconteam.bapp.ui.main.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.models.Mensaje
import com.falconteam.bapp.domain.usecases.chat.EnviarMensajeChatUseCase
import com.falconteam.bapp.domain.usecases.chat.ObtenerMensajesChatUseCase
import com.falconteam.bapp.ui.main.chat.ChatUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val enviarMensajeUseCase: EnviarMensajeChatUseCase,
    private val obtenerMensajesUseCase: ObtenerMensajesChatUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState

    fun loadMessages(conversacionId: String) {
        viewModelScope.launch {
            _uiState.value = ChatUiState(isLoading = true)
            try {
                val messages = obtenerMensajesUseCase(conversacionId)
                _uiState.value = ChatUiState(messages = messages)
            } catch (e: Exception) {
                _uiState.value = ChatUiState(error = e.message)
            }
        }
    }

    fun sendMessage(mensaje: Mensaje, conversacionId: String) {
        viewModelScope.launch {
            try {
                enviarMensajeUseCase(mensaje)
                loadMessages(conversacionId)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
