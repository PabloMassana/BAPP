package com.falconteam.bapp.ui.main.childparent

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.falconteam.bapp.data.models.*

class ChildParentViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ChildParentUiState())
    val uiState: StateFlow<ChildParentUiState> = _uiState

    fun setInitialData(
        alumno: Alumno,
        reportes: List<Reporte>,
        tareasPendientes: List<Task>,
        tareasCompletadas: List<Task>
    ) {
        _uiState.value = ChildParentUiState(
            alumno = alumno,
            reportes = reportes,
            tareasPendientes = tareasPendientes,
            tareasCompletadas = tareasCompletadas
        )
    }

    fun onTaskCheckedChange(task: Task, isChecked: Boolean) {
        _uiState.update { state ->
            val nuevasPendientes = state.tareasPendientes.filterNot { it.id == task.id }
            val nuevasCompletadas = state.tareasCompletadas + task.copy(completada = true)
            state.copy(
                tareasPendientes = nuevasPendientes,
                tareasCompletadas = nuevasCompletadas
            )
        }
    }
}
