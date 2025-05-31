package com.falconteam.bapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.models.Mensaje
import com.falconteam.bapp.ui.viewmodel.state.ChatUiState
import com.falconteam.bapp.utils.SupabaseUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState

    fun loadMessages() {
        viewModelScope.launch {
            _uiState.value = ChatUiState(isLoading = true)
            try {
                val messages = SupabaseUtils.postgrest
                    .from("chat_messages")
                    .select()
                    .decodeList<Mensaje>()
                _uiState.value = ChatUiState(messages = messages)
            } catch (e: Exception) {
                _uiState.value = ChatUiState(error = e.message)
            }
        }
    }

    fun sendMessage(message: Mensaje) {
        viewModelScope.launch {
            try {
                SupabaseUtils.postgrest
                    .from("chat_messages")
                    .insert(message)
                loadMessages()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
