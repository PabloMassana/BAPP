package com.falconteam.bapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.utils.SupabaseUtils
import io.github.jan.supabase.gotrue.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                SupabaseUtils.auth.signInWith(Email = email, Password = password)
                _user.value = SupabaseUtils.auth.currentUserOrNull()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                SupabaseUtils.auth.signOut()
                _user.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
