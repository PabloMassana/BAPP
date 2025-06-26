package com.falconteam.bapp.ui.admin.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.falconteam.bapp.R

data class User(val name: String, val avatarRes: Int)

class DeleteUserViewModel : ViewModel() {

    val groups = listOf("Kinder 4 - Sección A", "Kinder 4 - Sección B")

    var users = mutableStateListOf(
        User("Juanito Alejandro López", R.drawable.avatar1),
        User("Sofía Martínez López", R.drawable.avatar2),
        User("Mateo Ramírez Torres", R.drawable.avatar3),
        User("Juanito Alejandro López", R.drawable.avatar1),
        User("Sofía Martínez López", R.drawable.avatar2),
        User("Mateo Ramírez Torres", R.drawable.avatar3)
    )

    fun deleteUser(user: User) {
        users.remove(user)
    }
}
