package com.falconteam.bapp.ui.main.perfil

import com.falconteam.bapp.data.models.Usuario

data class PerfilUiState(
    val isLoading: Boolean = false,
    val usuario: UserEntity? = null,
    val error: String? = null
)