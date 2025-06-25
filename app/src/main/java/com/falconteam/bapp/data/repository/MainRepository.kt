package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.entity.UserEntity
import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.data.models.Alumno
import com.falconteam.bapp.data.models.Bitacora
import com.falconteam.bapp.data.models.Evidencia
import com.falconteam.bapp.data.models.Indicador
import com.falconteam.bapp.data.models.Mensaje
import com.falconteam.bapp.data.models.Notificacion
import com.falconteam.bapp.data.models.Reporte
import com.falconteam.bapp.data.models.Usuario
import com.falconteam.bapp.data.supabase.SupabaseManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MainRepository {
    suspend fun enviarMensaje(mensaje: Mensaje)
    suspend fun obtenerMensajes(conversacionId: String): List<Mensaje>

    suspend fun obtenerEvidencias(idCurso: String): List<Evidencia>
    suspend fun subirEvidencia(evidencia: Evidencia, fileBytes: ByteArray)

    suspend fun obtenerIndicadores(idAlumno: String): List<Indicador>
    suspend fun subirIndicador(indicador: Indicador)

    suspend fun obtenerBitacorasPorAlumno(idAlumno: String): List<Bitacora>
    suspend fun agregarBitacora(bitacora: Bitacora)

    suspend fun obtenerNotificaciones(usuarioId: String): List<Notificacion>
    suspend fun marcarNotificacionComoLeida(notificacionId: String)

    suspend fun obtenerUsuario(userId: String): UserEntity
    suspend fun actualizarRol(userId: String, nuevoRol: String)
    suspend fun cerrarSesion()

    suspend fun obtenerAlumnoActual(): Alumno
    suspend fun obtenerActividadesPorAlumno(alumnoId: String): List<Actividad>
    suspend fun obtenerUltimoReportePorAlumno(alumnoId: String): Reporte?

}

class MainRepositoryImpl(
    private val supabaseManager: SupabaseManager
) : MainRepository {

    override suspend fun enviarMensaje(mensaje: Mensaje) = withContext(Dispatchers.IO) {
        supabaseManager.insertarMensajeChat(mensaje)
    }

    override suspend fun obtenerMensajes(conversacionId: String): List<Mensaje> = withContext(Dispatchers.IO) {
        supabaseManager.obtenerMensajesChat(conversacionId)
    }

    override suspend fun obtenerEvidencias(idCurso: String): List<Evidencia> = withContext(Dispatchers.IO) {
        supabaseManager.obtenerEvidencias(idCurso)
    }

    override suspend fun subirEvidencia(evidencia: Evidencia, fileBytes: ByteArray) = withContext(Dispatchers.IO) {
        supabaseManager.subirEvidencia(evidencia, fileBytes)
    }

    override suspend fun obtenerIndicadores(idAlumno: String): List<Indicador> = withContext(Dispatchers.IO) {
        supabaseManager.obtenerIndicadores(idAlumno)
    }

    override suspend fun subirIndicador(indicador: Indicador) = withContext(Dispatchers.IO) {
        supabaseManager.subirIndicador(indicador)
    }

    override suspend fun obtenerBitacorasPorAlumno(idAlumno: String): List<Bitacora> = withContext(Dispatchers.IO) {
        supabaseManager.obtenerBitacorasPorAlumno(idAlumno)
    }

    override suspend fun agregarBitacora(bitacora: Bitacora) = withContext(Dispatchers.IO) {
        supabaseManager.agregarBitacora(bitacora)
    }

    override suspend fun obtenerNotificaciones(usuarioId: String): List<Notificacion> = withContext(Dispatchers.IO) {
        supabaseManager.obtenerNotificaciones(usuarioId)
    }

    override suspend fun marcarNotificacionComoLeida(notificacionId: String) = withContext(Dispatchers.IO) {
        supabaseManager.marcarNotificacionComoLeida(notificacionId)
    }

    override suspend fun obtenerUsuario(userId: String): UserEntity {
        return supabaseManager.obtenerUsuarioPorId(userId)
    }

    override suspend fun actualizarRol(userId: String, nuevoRol: String) {
        supabaseManager.actualizarRolUsuario(userId, nuevoRol)
    }

    override suspend fun cerrarSesion() {
        supabaseManager.cerrarSesion()
    }

    override suspend fun obtenerAlumnoActual(): Alumno {
        return supabaseManager.getAlumnoActual()
    }

    override suspend fun obtenerActividadesPorAlumno(alumnoId: String): List<Actividad> {
        return supabaseManager.getActividades(alumnoId)
    }

    override suspend fun obtenerUltimoReportePorAlumno(alumnoId: String): Reporte? {
        return supabaseManager.getUltimoReporte(alumnoId)
    }

}

