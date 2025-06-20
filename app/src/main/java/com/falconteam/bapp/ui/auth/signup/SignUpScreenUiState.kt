package com.falconteam.bapp.ui.auth.signup

data class SignUpScreenUiState(
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val isLoading: Boolean = false,
    val signUpSuccessful: Boolean = false,
    val errorMessage: String? = null,
    val idUsuario: String = ""
)

