package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.entity.ParentEntity
import com.falconteam.bapp.data.entity.UserEntity
import com.falconteam.bapp.data.local.DataStoreHelper
import com.falconteam.bapp.data.local.DataStoreHelper.Companion.USER_ID
import com.falconteam.bapp.data.local.DataStoreHelper.Companion.USER_JWT_TOKEN_KEY
import com.falconteam.bapp.data.local.DataStoreHelper.Companion.USER_ROLE
import com.falconteam.bapp.data.models.Rol
import com.falconteam.bapp.data.supabase.SupabaseManager
import io.github.jan.supabase.auth.user.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthRepository {
    suspend fun retrieveUserSessionOrNull(): Result<UserInfo?>
    suspend fun login(email: String, password: String): Result<UserEntity>
    suspend fun signUp(email: String, password: String, username: String): Result<UserInfo?>
    suspend fun logout(): Result<Boolean>
    suspend fun saveUser(userEntity: UserEntity): UserEntity?
    suspend fun getUser(idUsuario: String): Result<UserEntity>
}

class AuthRepositoryImpl(
    private val supabaseManager: SupabaseManager,
    private val dataStoreHelper: DataStoreHelper
) : AuthRepository {

    override suspend fun retrieveUserSessionOrNull(): Result<UserInfo?> = withContext(Dispatchers.IO) {
        try {
            val token = dataStoreHelper.getValue(USER_JWT_TOKEN_KEY, "")

            if (token.isEmpty()) {
                // User is not logged in
                return@withContext Result.success(null)
            } else {
                val user = supabaseManager.retrieveUserSession(token)
                val newToken = supabaseManager.getSessionTokenOrNull()
                dataStoreHelper.putValue(USER_JWT_TOKEN_KEY, newToken.toString())

                return@withContext Result.success(user)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun login(email: String, password: String): Result<UserEntity> =
        withContext(Dispatchers.IO) {
            try {
                val token = supabaseManager.loginSupabase(email, password)

                if (!token.isNullOrEmpty()) {
                    val userDb = supabaseManager.obtenerUsuarioPorEmail(email)
                    userDb?.let {
                        dataStoreHelper.putValue(USER_ID, it.id.orEmpty())
                        dataStoreHelper.putValue(USER_ROLE, it.rol)
                        dataStoreHelper.putValue(USER_JWT_TOKEN_KEY, token)
                        return@withContext Result.success(it)
                    }
                }

                Result.failure(Throwable(message = "Ups, no hemos podido iniciarte sesion, intenta nuevamente"))
            } catch (e: Exception) {
                // Handle errors
                e.printStackTrace()
                Result.failure(e)
            }
        }

    override suspend fun signUp(
        email: String,
        password: String,
        username: String
    ): Result<UserInfo?> = withContext(Dispatchers.IO) {
        try {
            val userInfo = supabaseManager.signUpUserSupabase(email, password, username)
            val userEntity = UserEntity(
                id = userInfo?.id,
                username = username,
                email = email,
                rol = Rol.PADRE.name
            )
            val parentEntity = ParentEntity(
                name = username,
                lastname = ""
            )

            val savedUser = saveUser(userEntity)

            if (savedUser != null) {
                supabaseManager.insertarPadre(parentEntity)
                dataStoreHelper.putValue(USER_ID, savedUser.id.orEmpty())
                dataStoreHelper.putValue(USER_ROLE, savedUser.rol)
            }

            Result.success(userInfo)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun logout(): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            supabaseManager.cerrarSesion()
            dataStoreHelper.clearAll()
            Result.success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun saveUser(userEntity: UserEntity): UserEntity? =
        withContext(Dispatchers.IO) {
            try {
                val user = supabaseManager.insertarUsuario(userEntity)
                user
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    override suspend fun getUser(idUsuario: String): Result<UserEntity> =
        withContext(Dispatchers.IO) {
            try {
                val user = supabaseManager.obtenerUsuarioPorId(idUsuario)
                Result.success(user)
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            }
        }

}