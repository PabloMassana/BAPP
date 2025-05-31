package com.falconteam.bapp.ui.viewmodel.state

import com.falconteam.bapp.data.models.Notificacion

data class NotificacionUiState(
    val isLoading: Boolean = false,
    val notificaciones: List<Notificacion> = emptyList(),
    val error: String? = null
)
