package com.falconteam.bapp.ui.viewmodel.state

import com.falconteam.bapp.data.models.Bitacora

data class BitacoraUiState(
    val isLoading: Boolean = false,
    val bitacoras: List<Bitacora> = emptyList(),
    val error: String? = null
)
