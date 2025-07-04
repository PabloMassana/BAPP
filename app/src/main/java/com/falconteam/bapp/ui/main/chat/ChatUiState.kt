package com.falconteam.bapp.ui.main.chat

data class ChatUiState(
    val isLoading: Boolean = false,
    val messages: List<Mensaje> = emptyList(),
    val error: String? = null
)