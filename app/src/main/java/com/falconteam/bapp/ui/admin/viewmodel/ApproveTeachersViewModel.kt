package com.falconteam.bapp.ui.admin.viewmodel

import androidx.lifecycle.ViewModel
import com.falconteam.bapp.ui.admin.state.ApproveTeachersState
import com.falconteam.bapp.ui.admin.state.Teacher
import com.falconteam.bapp.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ApproveTeachersViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        ApproveTeachersState(
            teachers = listOf(
                Teacher("1", "Juanito Alejandro López", R.drawable.avatar1),
                Teacher("2", "Sofía Martínez López", R.drawable.avatar2),
                Teacher("3", "Mateo Ramírez Torres", R.drawable.avatar3),
            )
        )
    )
    val state: StateFlow<ApproveTeachersState> = _state

    fun updateSearch(query: String) {
        _state.update { it.copy(searchQuery = query) }
    }

    fun approveTeacher(id: String) {
        // You can implement actual logic here
        println("Approved teacher with id: $id")
    }

    fun rejectTeacher(id: String) {
        // You can implement actual logic here
        println("Rejected teacher with id: $id")
    }
}
