package com.falconteam.bapp.ui.main.tasks

import com.falconteam.bapp.data.models.Bitacora

data class BitacoraUiState(
    val isLoading: Boolean = false,
    val bitacoras: List<Bitacora> = emptyList(),
    val error: String? = null
)