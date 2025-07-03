package com.falconteam.bapp.data.supabase

import com.falconteam.bapp.data.entity.main.ParentEntity
import com.falconteam.bapp.data.entity.main.TeacherEntity
import com.falconteam.bapp.data.entity.main.UserEntity
import com.falconteam.bapp.data.models.*

import io.github.jan.supabase.auth.user.UserInfo

interface SupabaseManager {

    // Usuarios
    fun getSessionTokenOrNull(): String?
    suspend fun loginSupabase(emailUser: String, passwordUser: String): String?
    suspend fun signUpUserSupabase(emailUser: String, passwordUser: String, userName: String): UserInfo?
    suspend fun cerrarSesion()
    suspend fun insertarUsuario(usuario: UserEntity): UserEntity
    suspend fun obtenerUsuarioPorId(idUsuario: String): UserEntity
    suspend fun obtenerUsuarioPorEmail(email: String): UserEntity?

    // padres
    suspend fun insertarPadre(padre: ParentEntity): ParentEntity?
    suspend fun obtenerListadoPadres(): List<ParentEntity>

    suspend fun insertarMensajeChat(mensaje: Mensaje)
    suspend fun obtenerMensajesChat(conversacionId: String): List<Mensaje>

    suspend fun obtenerEvidencias(idCurso: String): List<Evidencia>
    suspend fun subirEvidencia(evidencia: Evidencia, fileBytes: ByteArray)

    suspend fun obtenerIndicadores(idAlumno: String): List<Indicador>
    suspend fun subirIndicador(indicador: Indicador)

    suspend fun obtenerBitacorasPorAlumno(idAlumno: String): List<Bitacora>
    suspend fun agregarBitacora(bitacora: Bitacora)

    suspend fun obtenerNotificaciones(usuarioId: String): List<Notificacion>
    suspend fun marcarNotificacionComoLeida(notificacionId: String)

    suspend fun actualizarRolUsuario(userId: String, nuevoRol: String)

    suspend fun getAlumnoActual(): Alumno
    suspend fun getActividades(alumnoId: String): List<Actividad>
    suspend fun getUltimoReporte(alumnoId: String): Reporte?

    //Profesores
    suspend fun getMaestroActual(): TeacherEntity
    suspend fun obtenerCursosPorMaestro(idMaestro: String): List<Curso>
    suspend fun getActividadesPorMaestro(maestroId: String): List<Actividad>
    suspend fun agregarActividad(actividad: Actividad, fileBytes: ByteArray): Actividad

}