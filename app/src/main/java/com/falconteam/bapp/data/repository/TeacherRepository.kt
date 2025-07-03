package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.entity.TeacherEntity
import com.falconteam.bapp.data.local.DataStoreHelper
import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.data.models.Curso
import com.falconteam.bapp.data.supabase.SupabaseManager
import com.falconteam.bapp.domain.models.ActividadModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface TeacherRepository {
    suspend fun obtenerMaestroActual(): TeacherEntity
    suspend fun obtenerCursosPorMaestro(idMaestro: String): List<Curso>
    suspend fun obtenerActividadesPorMaestro(maestroId: String): List<ActividadModel>
    suspend fun upsertActividad(model: ActividadModel)
}

class TeacherRepositoryImpl (
    private val supabaseManager: SupabaseManager,
    private val dataStoreHelper: DataStoreHelper
) : TeacherRepository {

    override suspend fun obtenerMaestroActual(): TeacherEntity {
        return supabaseManager.getMaestroActual() // Obtiene el maestro actual
    }

    override suspend fun obtenerCursosPorMaestro(idMaestro: String): List<Curso> {
        return supabaseManager.obtenerCursosPorMaestro(idMaestro) // Obtiene cursos del maestro
    }

    override suspend fun obtenerActividadesPorMaestro(maestroId: String): List<ActividadModel> = withContext(Dispatchers.IO) {
        supabaseManager.getActividadesPorMaestro(maestroId) // Obtiene las actividades del maestro desde la base de datos
            .map { ActividadModel.fromEntity(it) } // Convierte las actividades de entidad a modelo
    }

    override suspend fun upsertActividad(model: ActividadModel) = withContext(Dispatchers.IO) {
        val entity = model.toEntity() // Convierte el modelo de actividad a entidad
        supabaseManager.upsertActividad(entity) // Inserta o actualiza la actividad en la base de datos
    }
}
