package com.falconteam.bapp.ui.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.data.local.DataStoreHelper
import com.falconteam.bapp.domain.usecases.auth.LoginUseCase
import com.falconteam.bapp.domain.usecases.auth.ObtenerSesionUsuarioUseCase
import com.falconteam.bapp.utils.UserRole
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val obtenerSesionUsuarioUseCase: ObtenerSesionUsuarioUseCase,
    private val dataStoreHelper: DataStoreHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginScreenUiState())
    val uiState: StateFlow<LoginScreenUiState> = _uiState.asStateFlow()

    init {
        onStart()
    }

    private fun onStart() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            obtenerSesionUsuarioUseCase().onSuccess { user ->
                if (user == null) {
                    _uiState.update { it.copy(loading = false) }
                    return@onSuccess
                }

                val userRole = dataStoreHelper.getValue(DataStoreHelper.USER_ROLE, "")
                _uiState.update {
                    it.copy(
                        loginSuccessful = true,
                        userRole = UserRole.getRoleFromString(userRole),
                        loading = false
                    )
                }
            }.onFailure { e ->
                _uiState.update { it.copy(errorMessage = e.message, loading = false) }
            }
        }
    }

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun loginAction() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }

            val result = loginUseCase(
                email = _uiState.value.email,
                password = _uiState.value.password
            )

            result.onSuccess { user ->
                _uiState.update {
                    it.copy(
                        loginSuccessful = true,
                        userRole = UserRole.getRoleFromString(user.rol),
                        loading = false
                    )
                }
            }.onFailure { e ->
                _uiState.update { it.copy(errorMessage = e.message, loading = false) }
            }
        }
    }
}