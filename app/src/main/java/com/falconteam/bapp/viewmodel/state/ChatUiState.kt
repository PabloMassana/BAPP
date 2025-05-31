package com.falconteam.bapp.ui.viewmodel.state

import com.falconteam.bapp.data.models.Mensaje

data class ChatUiState(
    val isLoading: Boolean = false,
    val messages: List<Mensaje> = emptyList(),
    val error: String? = null
)
