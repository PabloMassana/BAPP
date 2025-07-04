
package com.falconteam.bapp.ui.main.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.usecases.chat.EnviarMensajeChatUseCase
import com.falconteam.bapp.domain.usecases.chat.ObtenerMensajesChatUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(
    private val enviarMensajeUseCase: EnviarMensajeChatUseCase,
    private val obtenerMensajesUseCase: ObtenerMensajesChatUseCase
) : ViewModel() {

    private val _chatUiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _chatUiState

    fun loadMessages(conversacionId: String) {
        viewModelScope.launch {
            _chatUiState.value = ChatUiState(isLoading = true)

            try {
                val mensajes = obtenerMensajesUseCase(conversacionId)
                _chatUiState.value = ChatUiState(messages = mensajes)
            } catch (error: Exception) {
                _chatUiState.value = ChatUiState(error = error.message)
            }
        }
    }

    fun sendMessage(mensaje: Mensaje, conversacionId: String) {
        viewModelScope.launch {
            try {
                enviarMensajeUseCase(mensaje)

                // Después de enviar, recargamos los mensajes
                loadMessages(conversacionId)

            } catch (error: Exception) {
                _chatUiState.value = _chatUiState.value.copy(error = error.message)
            }
        }
    }
}
