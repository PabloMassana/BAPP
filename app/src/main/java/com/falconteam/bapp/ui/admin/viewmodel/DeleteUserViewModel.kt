package com.falconteam.bapp.ui.admin.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.falconteam.bapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class User(
    val name: String,
    val avatarRes: Int,
    val group: String
)

class DeleteUserViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val groups = listOf("Kinder 4 - Sección A", "Kinder 4 - Sección B")

    private val _users = MutableStateFlow(
        savedStateHandle.get<List<User>>("users") ?: listOf(
            // Sección A
            User("Juanito Alejandro López", R.drawable.avatar1, "Kinder 4 - Sección A"),
            User("Sofía Martínez López", R.drawable.avatar2, "Kinder 4 - Sección A"),
            User("Mateo Ramírez Torres", R.drawable.avatar3, "Kinder 4 - Sección A"),

            // Sección B
            User("Camila Herrera Cruz", R.drawable.avatar2, "Kinder 4 - Sección B"),
            User("Luis Fernando Gómez", R.drawable.avatar3, "Kinder 4 - Sección B"),
            User("Andrés Esteban Rivas", R.drawable.avatar1, "Kinder 4 - Sección B")
        )
    )

    val users: StateFlow<List<User>> = _users

    fun deleteUser(user: User) {
        _users.update { current ->
            val updated = current.toMutableList().apply { remove(user) }
            savedStateHandle["users"] = updated
            updated
        }
    }
}
