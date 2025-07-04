package com.falconteam.bapp.data.supabase

import com.falconteam.bapp.data.entity.CourseEntity
import com.falconteam.bapp.data.entity.ActividadEntity
import com.falconteam.bapp.data.entity.ParentEntity
import com.falconteam.bapp.data.entity.StudentCourseEntity
import com.falconteam.bapp.data.entity.StudentEntity
import com.falconteam.bapp.data.entity.TeacherEntity
import com.falconteam.bapp.data.entity.UserEntity
import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.data.models.Alumno
import com.falconteam.bapp.data.models.Bitacora
import com.falconteam.bapp.data.models.Curso
import com.falconteam.bapp.data.models.Evidencia
import com.falconteam.bapp.data.models.Indicador
import com.falconteam.bapp.data.models.Mensaje
import com.falconteam.bapp.data.models.Notificacion
import com.falconteam.bapp.data.models.Reporte
import io.github.jan.supabase.auth.user.UserInfo
import io.github.jan.supabase.postgrest.result.PostgrestResult

interface SupabaseManager {

    // Usuarios
    fun getSessionTokenOrNull(): String?
    suspend fun retrieveUserSession(token: String): UserInfo
    suspend fun loginSupabase(emailUser: String, passwordUser: String): String?
    suspend fun signUpUserSupabase(emailUser: String, passwordUser: String, userName: String): UserInfo?
    suspend fun cerrarSesion()
    suspend fun insertarUsuario(usuario: UserEntity): UserEntity
    suspend fun obtenerUsuarioPorId(idUsuario: String): UserEntity
    suspend fun obtenerUsuarioPorEmail(email: String): UserEntity?

    // Padres
    suspend fun insertarPadre(padre: ParentEntity): ParentEntity?
    suspend fun obtenerListadoPadres(): List<ParentEntity>
    suspend fun eliminarPadre(padreId: Int): ParentEntity

    // grupos
    suspend fun insertarGrupo(grupo: CourseEntity): CourseEntity
    suspend fun obtenerGrupos(): List<CourseEntity>

    // alumnos
    suspend fun obtenerAlumnos(): List<StudentEntity>

    suspend fun insertarAlumno(alumno: StudentEntity): StudentEntity

    suspend fun insertarAlumnoCurso(studentCourseEntity: StudentCourseEntity): StudentCourseEntity

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

    // Profesores
    suspend fun insertarMaestro(maestro: TeacherEntity): TeacherEntity
    suspend fun getMaestroActual(): TeacherEntity
    suspend fun obtenerCursosPorMaestro(idMaestro: String): List<Curso>
    suspend fun getActividadesPorMaestro(maestroId: String): List<ActividadEntity>
    suspend fun upsertActividad(actividadEntity: ActividadEntity)

}
