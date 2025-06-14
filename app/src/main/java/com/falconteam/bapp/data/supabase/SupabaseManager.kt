package com.falconteam.bapp.data.supabase

import com.falconteam.bapp.data.models.Bitacora
import com.falconteam.bapp.data.models.Evidencia
import com.falconteam.bapp.data.models.Indicador
import com.falconteam.bapp.data.models.Mensaje
import com.falconteam.bapp.data.models.Notificacion
import com.falconteam.bapp.data.models.Usuario
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.user.UserInfo
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.storage.storage

interface SupabaseManager {
    suspend fun loginSupabase(emailUser: String, passwordUser: String)
    suspend fun signUpUserSupabase(emailUser: String, passwordUser: String): UserInfo?
    suspend fun cerrarSesion()

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

    suspend fun obtenerUsuarioPorId(userId: String): Usuario
    suspend fun actualizarRolUsuario(userId: String, nuevoRol: String)
}

class SupabaseManagerImpl(
    private val supabaseClient: SupabaseClient
) : SupabaseManager {

    override suspend fun loginSupabase(emailUser: String, passwordUser: String) {
        supabaseClient.auth.signInWith(Email) {
            email = emailUser
            password = passwordUser
        }
    }

    override suspend fun signUpUserSupabase(emailUser: String, passwordUser: String): UserInfo? {
        return supabaseClient.auth.signUpWith(Email) {
            email = emailUser
            password = passwordUser
        }
    }

    override suspend fun cerrarSesion() {
        supabaseClient.auth.signOut()
    }

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
        val bucket = supabaseClient.storage.from("evidencias") // corregido "evidecias" → "evidencias"
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
                update = mapOf("leida" to true)
            ) {
                filter { eq("id", notificacionId) }
            }
    }

    override suspend fun obtenerUsuarioPorId(userId: String): Usuario {
        return supabaseClient.from("usuarios")
            .select {
                filter { eq("id", userId) }
            }
            .decodeSingle()
    }

    override suspend fun actualizarRolUsuario(userId: String, nuevoRol: String) {
        supabaseClient.from("usuarios")
            .update(
                update = mapOf("rol" to nuevoRol)
            ) {
                filter { eq("id", userId) }
            }
    }
}