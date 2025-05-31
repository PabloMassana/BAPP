package com.falconteam.bapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.models.Indicador
import com.falconteam.bapp.ui.viewmodel.state.IndicadoresUiState
import com.falconteam.bapp.utils.SupabaseUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IndicadoresViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(IndicadoresUiState())
    val uiState: StateFlow<IndicadoresUiState> = _uiState

    fun loadIndicadores() {
        viewModelScope.launch {
            _uiState.value = IndicadoresUiState(isLoading = true)
            try {
                val indicadores = SupabaseUtils.postgrest
                    .from("indicadores")
                    .select()
                    .decodeList<Indicador>()
                _uiState.value = IndicadoresUiState(indicadores = indicadores)
            } catch (e: Exception) {
                _uiState.value = IndicadoresUiState(error = e.message)
            }
        }
    }

    fun addIndicador(indicador: Indicador) {
        viewModelScope.launch {
            try {
                SupabaseUtils.postgrest
                    .from("indicadores")
                    .insert(indicador)
                loadIndicadores()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
