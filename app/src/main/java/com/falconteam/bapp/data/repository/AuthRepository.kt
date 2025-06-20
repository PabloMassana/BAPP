package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.entity.UserEntity
import com.falconteam.bapp.data.models.Rol
import com.falconteam.bapp.data.models.Usuario
import com.falconteam.bapp.data.supabase.SupabaseManager
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthRepository {
    suspend fun login(email: String, password: String)
    suspend fun signUp(email: String, password: String, username: String): Result<UserInfo?>
    suspend fun saveUser(userEntity: UserEntity): UserEntity?
    suspend fun getUser(idUsuario: String): Result<UserEntity>
}

class AuthRepositoryImpl(
    private val supabaseManager: SupabaseManager
): AuthRepository {

    override suspend fun login(email: String, password: String) = withContext(Dispatchers.IO) {
        try {
            supabaseManager.loginSupabase(email, password)
            val userInfo = supabaseManager.loginSupabase(email, password)

            //crear logica para obtener el usuario de la base de datos
        } catch (e: Exception) {
            // Handle errors
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        username: String
    ): Result<UserInfo?> = withContext(Dispatchers.IO) {
        try {
            val userInfo = supabaseManager.signUpUserSupabase(email, password, username)
            val userEntity = UserEntity(username = username, email = email, rol = Rol.PADRE.name)
            saveUser(userEntity)

            Result.success(userInfo)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun saveUser(userEntity: UserEntity): UserEntity? = withContext(Dispatchers.IO) {
        try {
            val user = supabaseManager.insertarUsuario(userEntity)
            user
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getUser(idUsuario: String): Result<UserEntity> = withContext(Dispatchers.IO) {
        try {
            val user = supabaseManager.obtenerUsuario(idUsuario)
            Result.success(user)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

}