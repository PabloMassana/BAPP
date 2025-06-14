package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.supabase.SupabaseManager
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun signUp(email: String, password: String): UserInfo?
}

class AuthRepositoryImpl(
    private val supabaseManager: SupabaseManager
): AuthRepository {

    override suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            supabaseManager.loginSupabase(email, password)
            //crear logica para obtener el usuario de la base de datos
        } catch (e: Exception) {
            // Handle errors
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): UserInfo? = withContext(Dispatchers.IO) {
        try {
            supabaseManager.signUpUserSupabase(email, password)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}