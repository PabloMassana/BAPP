package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.models.*
import kotlinx.coroutines.tasks.await

class SupabaseRepository {

    private val db = FirebaseService.db

    // === BITÁCORAS ===
    suspend fun agregarBitacora(bitacora: Bitacora) {
        db.collection("bitacoras").document(bitacora.id).set(bitacora).await()
    }

    suspend fun obtenerBitacorasPorAlumno(alumnoId: String): List<Bitacora> {
        return db.collection("bitacoras")
            .whereEqualTo("alumnoId", alumnoId)
            .get().await()
            .documents.mapNotNull { it.toObject<Bitacora>() }
    }

    // === EVIDENCIAS ===
    suspend fun subirEvidencia(evidencia: Evidencia) {
        db.collection("evidencias").document(evidencia.id).set(evidencia).await()
    }

    suspend fun obtenerEvidenciasPorAlumno(alumnoId: String): List<Evidencia> {
        return db.collection("evidencias")
            .whereEqualTo("alumnoId", alumnoId)
            .get().await()
            .documents.mapNotNull { it.toObject<Evidencia>() }
    }

    // === INDICADORES ===
    suspend fun agregarIndicador(indicador: Indicador) {
        db.collection("indicadores").document(indicador.id).set(indicador).await()
    }

    suspend fun obtenerIndicadoresPorAlumno(alumnoId: String): List<Indicador> {
        return db.collection("indicadores")
            .whereEqualTo("alumnoId", alumnoId)
            .get().await()
            .documents.mapNotNull { it.toObject<Indicador>() }
    }

    // === NOTIFICACIONES ===
    suspend fun obtenerNotificaciones(): List<Notificacion> {
        return db.collection("notificaciones")
            .get().await()
            .documents.mapNotNull { it.toObject<Notificacion>() }
    }

    // === USUARIO ===
    suspend fun obtenerUsuarioPorId(userId: String): Usuario? {
        return db.collection("usuarios").document(userId)
            .get().await().toObject<Usuario>()
    }

    suspend fun registrarUsuario(usuario: Usuario) {
        db.collection("usuarios").document(usuario.id).set(usuario).await()
    }

    // === ALUMNOS ===
    suspend fun obtenerAlumnoPorId(alumnoId: String): Alumno? {
        return db.collection("alumnos").document(alumnoId)
            .get().await().toObject<Alumno>()
    }

    suspend fun obtenerAlumnosPorMaestro(maestroId: String): List<Alumno> {
        return db.collection("alumnos")
            .whereEqualTo("maestroId", maestroId)
            .get().await()
            .documents.mapNotNull { it.toObject<Alumno>() }
    }

    suspend fun obtenerAlumnosPorPadre(padreId: String): List<Alumno> {
        return db.collection("alumnos")
            .whereEqualTo("padreId", padreId)
            .get().await()
            .documents.mapNotNull { it.toObject<Alumno>() }
    }
}
