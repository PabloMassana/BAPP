package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.entity.main.TeacherEntity
import com.falconteam.bapp.data.local.DataStoreHelper
import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.data.models.Curso
import com.falconteam.bapp.data.supabase.SupabaseManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface TeacherRepository {
    suspend fun obtenerMaestroActual(): TeacherEntity
    suspend fun obtenerCursosPorMaestro(idMaestro: String): List<Curso>
    suspend fun obtenerActividadesPorMaestro(maestroId: String): List<Actividad>
    suspend fun agregarActividad(actividad: Actividad, fileBytes: ByteArray): Actividad
}

class TeacherRepositoryImpl (
    private val supabaseManager: SupabaseManager,
    private val dataStoreHelper: DataStoreHelper
) : TeacherRepository {
    override suspend fun obtenerMaestroActual(): TeacherEntity {
        return supabaseManager.getMaestroActual()
    }

    override suspend fun obtenerCursosPorMaestro(idMaestro: String): List<Curso> {
        return supabaseManager.obtenerCursosPorMaestro(idMaestro)
    }

    override suspend fun obtenerActividadesPorMaestro(maestroId: String): List<Actividad> = withContext(Dispatchers.IO) {
        supabaseManager.getActividadesPorMaestro(maestroId)
    }

    override suspend fun agregarActividad(actividad: Actividad, fileBytes: ByteArray): Actividad = withContext(Dispatchers.IO) {
        supabaseManager.agregarActividad(actividad, fileBytes)
    }
}

