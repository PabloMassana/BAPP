package com.falconteam.bapp.ui.auth.login

import com.falconteam.bapp.utils.Constants.EMPTY_STRING

data class LoginScreenUiState(
    val email: String = EMPTY_STRING,
    val password: String = EMPTY_STRING,
    val loginSuccessful: Boolean = false,
    val errorMessage: String? = null
)
