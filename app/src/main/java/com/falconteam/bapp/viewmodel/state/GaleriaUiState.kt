package com.falconteam.bapp.ui.viewmodel.state

import com.falconteam.bapp.data.models.Evidencia

data class GaleriaUiState(
    val isLoading: Boolean = false,
    val evidencias: List<Evidencia> = emptyList(),
    val error: String? = null
)
