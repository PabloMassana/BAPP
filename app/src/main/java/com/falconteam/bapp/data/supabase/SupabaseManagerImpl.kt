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
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserInfo
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.postgrest.result.PostgrestResult
import io.github.jan.supabase.storage.storage
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SupabaseManagerImpl(
    private val supabaseClient: SupabaseClient
) : SupabaseManager {

    override fun getSessionTokenOrNull(): String? = supabaseClient.auth.currentAccessTokenOrNull()

    override suspend fun retrieveUserSession(token: String): UserInfo {
        val user = supabaseClient.auth.retrieveUser(token)
        supabaseClient.auth.refreshCurrentSession()

        return user
    }

    override suspend fun loginSupabase(emailUser: String, passwordUser: String): String? {
        supabaseClient.auth.signInWith(Email) {
            email = emailUser
            password = passwordUser
        }

        return getSessionTokenOrNull()
    }

    override suspend fun signUpUserSupabase(
        emailUser: String,
        passwordUser: String,
        userName: String
    ): UserInfo? = supabaseClient.auth.signUpWith(Email) {
        email = emailUser
        password = passwordUser
        data = buildJsonObject {
            put("username", userName)
        }
    }

    override suspend fun cerrarSesion() {
        supabaseClient.auth.signOut()
    }

    override suspend fun insertarUsuario(usuario: UserEntity): UserEntity {
        return supabaseClient.from("usuario").insert(usuario) {
            select()
        }.decodeSingle<UserEntity>()
    }

    override suspend fun obtenerUsuarioPorId(idUsuario: String): UserEntity =
        supabaseClient.from("usuario").select {
            filter {
                eq("id", idUsuario)
            }
        }.decodeSingle<UserEntity>()

    override suspend fun obtenerUsuarioPorEmail(email: String): UserEntity? =
        supabaseClient.from("usuario").select {
            filter {
                eq("email", email)
            }
        }.decodeSingleOrNull<UserEntity>()

    override suspend fun insertarPadre(padre: ParentEntity): ParentEntity? =
        supabaseClient.from("padre").insert(padre) {
            select()
        }.decodeSingleOrNull<ParentEntity>()

    override suspend fun obtenerListadoPadres(): List<ParentEntity> =
        supabaseClient.from("padre").select {
            order("id", Order.ASCENDING)
        }.decodeList<ParentEntity>()

    override suspend fun eliminarPadre(padreId: Int): ParentEntity =
        supabaseClient.from("padre").delete {
            filter { eq("id", padreId) }
            select()
        }.decodeSingle()

    override suspend fun insertarGrupo(grupo: CourseEntity): CourseEntity =
        supabaseClient.from("curso").insert(grupo) {
            select()
        }.decodeSingle<CourseEntity>()

    override suspend fun obtenerGrupos(): List<CourseEntity> =
        supabaseClient.from("curso").select {
            order("id", Order.ASCENDING)
        }.decodeList()

    override suspend fun obtenerAlumnos(): List<StudentEntity> =
        supabaseClient.from("hijo").select {
            order("id", Order.ASCENDING)
        }.decodeList()

    override suspend fun insertarAlumno(alumno: StudentEntity): StudentEntity =
        supabaseClient.from("hijo").insert(alumno) {
            select()
        }.decodeSingle<StudentEntity>()

    override suspend fun insertarAlumnoCurso(
        studentCourseEntity: StudentCourseEntity
    ): StudentCourseEntity = supabaseClient.from("curso_alumno").insert(
        studentCourseEntity
    ) {
        select()
    }.decodeSingle<StudentCourseEntity>()

    override suspend fun insertarMensajeChat(mensaje: Mensaje) {
        supabaseClient.from("chat_messages").insert(mensaje)
    }

    override suspend fun obtenerMensajesChat(conversacionId: String): List<Mensaje> {
        return supabaseClient.from("chat_messages")
            .select {
                filter { eq("conversacionId", conversacionId) }
                order("timestamp", Order.ASCENDING)
            }
            .decodeList()
    }

    override suspend fun obtenerEvidencias(idCurso: String): List<Evidencia> {
        return supabaseClient.from("evidencias")
            .select {
                filter { eq("idCurso", idCurso) }
                order("fecha", Order.DESCENDING)
            }
            .decodeList()
    }

    override suspend fun subirEvidencia(evidencia: Evidencia, fileBytes: ByteArray) {
        val bucket =
            supabaseClient.storage.from("evidencias") // corregido "evidecias" → "evidencias"
        bucket.upload(evidencia.archivoUrl, fileBytes) {
            upsert = false
        }
        supabaseClient.from("evidencias").insert(evidencia)
    }

    override suspend fun obtenerIndicadores(idAlumno: String): List<Indicador> {
        return supabaseClient.from("indicadores")
            .select {
                filter { eq("idAlumno", idAlumno) }
                order("fecha", Order.DESCENDING)
            }
            .decodeList()
    }

    override suspend fun subirIndicador(indicador: Indicador) {
        supabaseClient.from("indicadores").insert(indicador)
    }

    override suspend fun obtenerBitacorasPorAlumno(idAlumno: String): List<Bitacora> {
        return supabaseClient.from("bitacoras")
            .select {
                filter { eq("idAlumno", idAlumno) }
                order("fecha", Order.DESCENDING)
            }
            .decodeList()
    }

    override suspend fun agregarBitacora(bitacora: Bitacora) {
        supabaseClient.from("bitacoras").insert(bitacora)
    }

    override suspend fun obtenerNotificaciones(usuarioId: String): List<Notificacion> {
        return supabaseClient.from("notificaciones")
            .select {
                filter { eq("destinatario_id", usuarioId) }
                order("fecha", Order.DESCENDING)
            }
            .decodeList()
    }

    override suspend fun marcarNotificacionComoLeida(notificacionId: String) {
        supabaseClient.from("notificaciones")
            .update(
                update = {}
            ) {
                filter { eq("id", notificacionId) }
            }
    }

    override suspend fun actualizarRolUsuario(userId: String, nuevoRol: String) {
        supabaseClient.from("usuario")
            .update(
                {
                    set("rol", nuevoRol)
                }
            ) {
                filter { eq("id", userId) }
            }
    }

    override suspend fun getAlumnoActual(): Alumno {
        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: throw IllegalStateException("Usuario no autenticado")

        return supabaseClient.from("alumnos")
            .select {
                filter {
                    eq("user_id", userId)
                }
            }
            .decodeSingle()
    }

    override suspend fun getActividades(alumnoId: String): List<Actividad> {
        return supabaseClient.from("actividades")
            .select {
                filter {
                    eq("alumno_id", alumnoId)
                }
                order("fecha", Order.DESCENDING)
            }
            .decodeList()
    }

    override suspend fun getUltimoReporte(alumnoId: String): Reporte? {
        return supabaseClient.from("reportes")
            .select {
                filter {
                    eq("alumno_id", alumnoId)
                }
                order("fecha", Order.DESCENDING)
                limit(1)
            }
            .decodeList<Reporte>()
            .firstOrNull()
    }

    override suspend fun insertarMaestro(maestro: TeacherEntity): TeacherEntity =
        supabaseClient.from("profesor").insert(maestro) {
            select()
        }.decodeSingle<TeacherEntity>()

    override suspend fun getMaestroActual(): TeacherEntity {
        val userId = supabaseClient.auth.currentUserOrNull()?.id
            ?: throw IllegalStateException("Usuario no autenticado")

        return supabaseClient.from("usuario")
            .select {
                filter { eq("id", userId) }
            }
            .decodeSingle()
    }

    override suspend fun obtenerCursosPorMaestro(idMaestro: String): List<Curso> {
        return supabaseClient.from("cursos")
            .select {
                filter { eq("idMaestro", idMaestro) }
                order("nombre", Order.ASCENDING)
            }
            .decodeList()
    }

    override suspend fun getActividadesPorMaestro(maestroId: String): List<ActividadEntity> {
        return supabaseClient.from("actividades")
            .select {
                filter { eq("maestro_id", maestroId) }
                order("fecha", Order.DESCENDING)
            }
            .decodeList()
    }

    override suspend fun upsertActividad(actividadEntity: ActividadEntity) {
        supabaseClient.from("actividades").upsert(actividadEntity) {
            select()
        }
    }

}