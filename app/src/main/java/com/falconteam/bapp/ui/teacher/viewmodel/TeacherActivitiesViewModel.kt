package com.yo.practicanding.ui.theme.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Actividad(
    val nombre: String,
    val fecha: String,
    val hora: String
    // Aquí podrías agregar una lista de imágenes u otros campos si es necesario
)

class TeacherActivitiesViewModel : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _actividades = MutableStateFlow<List<Actividad>>(emptyList())
    val actividades: StateFlow<List<Actividad>> = _actividades.asStateFlow()

    // Datos temporales para el formulario
    private val _nombreTemp = MutableStateFlow("")
    val nombreTemp: StateFlow<String> = _nombreTemp.asStateFlow()

    private val _fechaTemp = MutableStateFlow("")
    val fechaTemp: StateFlow<String> = _fechaTemp.asStateFlow()

    private val _horaTemp = MutableStateFlow("")
    val horaTemp: StateFlow<String> = _horaTemp.asStateFlow()

    fun actualizarBusqueda(texto: String) {
        _searchText.value = texto
    }

    fun actualizarNombreTemp(nombre: String) {
        _nombreTemp.value = nombre
    }

    fun actualizarFechaTemp(fecha: String) {
        _fechaTemp.value = fecha
    }

    fun actualizarHoraTemp(hora: String) {
        _horaTemp.value = hora
    }

    fun agregarActividad() {
        val nueva = Actividad(
            nombre = _nombreTemp.value,
            fecha = _fechaTemp.value,
            hora = _horaTemp.value
        )
        _actividades.value = _actividades.value + nueva
        limpiarFormulario()
    }

    fun limpiarFormulario() {
        _nombreTemp.value = ""
        _fechaTemp.value = ""
        _horaTemp.value = ""
    }
}
