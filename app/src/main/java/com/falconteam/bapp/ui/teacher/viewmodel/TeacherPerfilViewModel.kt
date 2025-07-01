package com.falconteam.bapp.ui.teacher.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Modelo del usuario (maestro)
data class UserEntity(
    val id: String = "",
    val username: String = "",
    val email: String = "",
    val rol: String = "",
    val fotoUrl: String? = null
)

class TeacherPerfilViewModel : ViewModel() {

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user: StateFlow<UserEntity?> = _user.asStateFlow()

    private val _groupName = MutableStateFlow("3°B")
    val groupName: StateFlow<String> = _groupName.asStateFlow()

    private val _students = MutableStateFlow(
        listOf(
            "Ana Martínez",
            "Carlos Ramírez",
            "Daniela López",
            "Esteban Gómez",
            "Fernanda Ruiz"
        )
    )
    val students: StateFlow<List<String>> = _students.asStateFlow()

    init {
        // Simulamos carga inicial
        _user.value = UserEntity(
            id = "1",
            username = "Juan Pérez",
            email = "juan.perez@escuela.com",
            rol = "Maestro de Matemáticas",
            fotoUrl = null
        )
    }

    // Métodos opcionales para modificar los datos
    fun actualizarNombre(nuevo: String) {
        _user.value = _user.value?.copy(username = nuevo)
    }

    fun actualizarCorreo(nuevo: String) {
        _user.value = _user.value?.copy(email = nuevo)
    }

    fun actualizarRol(nuevo: String) {
        _user.value = _user.value?.copy(rol = nuevo)
    }

    fun actualizarGrupo(nuevo: String) {
        _groupName.value = nuevo
    }

    fun actualizarEstudiantes(nuevaLista: List<String>) {
        _students.value = nuevaLista
    }
}
