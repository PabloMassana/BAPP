package com.falconteam.bapp.ui.admin.state

import com.falconteam.bapp.ui.admin.viewmodel.User

data class DeleteUserUiState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
