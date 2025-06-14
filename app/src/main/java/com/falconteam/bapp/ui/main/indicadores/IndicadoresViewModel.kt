package com.falconteam.bapp.ui.main.indicadores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.models.Indicador
import com.falconteam.bapp.domain.usecases.indicador.ObtenerIndicadoresUseCase
import com.falconteam.bapp.domain.usecases.indicador.SubirIndicadorUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IndicadoresViewModel(
    private val obtenerIndicadoresUseCase: ObtenerIndicadoresUseCase,
    private val subirIndicadorUseCase: SubirIndicadorUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(IndicadoresUiState())
    val uiState: StateFlow<IndicadoresUiState> = _uiState

    fun loadIndicadores(idAlumno: String) {
        viewModelScope.launch {
            _uiState.value = IndicadoresUiState(isLoading = true)
            try {
                val indicadores = obtenerIndicadoresUseCase(idAlumno)
                _uiState.value = IndicadoresUiState(indicadores = indicadores)
            } catch (e: Exception) {
                _uiState.value = IndicadoresUiState(error = e.message)
            }
        }
    }

    fun addIndicador(indicador: Indicador) {
        viewModelScope.launch {
            try {
                subirIndicadorUseCase(indicador)
                loadIndicadores(indicador.idAlumno)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            }
        }
    }
}
