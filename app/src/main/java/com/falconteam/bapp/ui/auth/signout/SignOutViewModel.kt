package com.falconteam.bapp.ui.auth.signout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignOutViewModel(
    private val supabaseClient: SupabaseClient
) : ViewModel() {

    private val _signOutSuccessful = MutableStateFlow(false)
    val signOutSuccessful: StateFlow<Boolean> = _signOutSuccessful.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun signOut() {
        viewModelScope.launch {
            try {
                supabaseClient.auth.signOut()
                _signOutSuccessful.value = true
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }
}
