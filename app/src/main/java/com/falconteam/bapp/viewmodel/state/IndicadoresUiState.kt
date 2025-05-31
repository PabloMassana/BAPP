package com.falconteam.bapp.ui.viewmodel.state

import com.falconteam.bapp.data.models.Indicador

data class IndicadoresUiState(
    val isLoading: Boolean = false,
    val indicadores: List<Indicador> = emptyList(),
    val error: String? = null
)
