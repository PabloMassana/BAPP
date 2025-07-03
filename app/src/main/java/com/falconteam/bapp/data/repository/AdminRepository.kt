package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.entity.ParentEntity
import com.falconteam.bapp.data.local.DataStoreHelper
import com.falconteam.bapp.data.supabase.SupabaseManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AdminRepository {
    suspend fun getAllParents(): Result<List<ParentEntity>>
}

class AdminRepositoryImpl(
    private val supabaseManager: SupabaseManager,
    private val dataStoreHelper: DataStoreHelper
) : AdminRepository {

    override suspend fun getAllParents(): Result<List<ParentEntity>> = withContext(Dispatchers.IO) {
        try {
            val parents = supabaseManager.obtenerListadoPadres()
            Result.success(parents)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

}