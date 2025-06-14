package com.falconteam.bapp.ui.main.indicadores

import com.falconteam.bapp.data.models.Indicador

data class IndicadoresUiState(
    val isLoading: Boolean = false,
    val indicadores: List<Indicador> = emptyList(),
    val error: String? = null
)
