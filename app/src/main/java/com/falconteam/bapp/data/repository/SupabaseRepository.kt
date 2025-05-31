package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.models.*
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

class SupabaseRepository(private val supabase: SupabaseClient) {

    suspend fun iniciarSesion(email: String, password: String): Usuario? {
        val result = supabase.auth.signInWith(email, password)
        return result?.user?.let { getUsuarioPorId(it.id) }
    }

    suspend fun getUsuarioPorId(id: String): Usuario? {
        return supabase.from("usuarios").select {
            filter { eq("id", id) }
        }.decodeSingleOrNull()
    }

    suspend fun obtenerRolUsuario(id: String): Rol? {
        return getUsuarioPorId(id)?.rol
    }

    suspend fun cambiarRolUsuario(usuarioId: String, nuevoRol: Rol) {
        val usuario = getUsuarioPorId(usuarioId)
        usuario?.let {
            val actualizado = it.copy(rol = nuevoRol)
            supabase.from("usuarios").update(actualizado) {
                eq("id", usuarioId)
            }
        }
    }

    suspend fun agregarBitacora(bitacora: Bitacora) {
        supabase.from("bitacoras").insert(bitacora)
    }

    suspend fun obtenerBitacorasPorAlumno(alumnoId: String): List<Bitacora> {
        return supabase.from("bitacoras").select {
            filter { eq("idAlumno", alumnoId) }
            order("fecha", ascending = false)
        }.decodeList()
    }

    suspend fun enviarMensaje(mensaje: Mensaje) {
        supabase.from("mensajes").insert(mensaje)
    }

    suspend fun obtenerMensajes(conversacionId: String): List<Mensaje> {
        return supabase.from("mensajes").select {
            filter { eq("conversacionId", conversacionId) }
            order("timestamp", ascending = true)
        }.decodeList()
    }

    suspend fun obtenerEvidencias(alumnoId: String): List<Evidencia> {
        return supabase.from("evidencias").select {
            filter { eq("idAlumno", alumnoId) }
            order("fecha", ascending = false)
        }.decodeList()
    }

    suspend fun subirEvidencia(evidencia: Evidencia) {
        supabase.from("evidencias").insert(evidencia)
    }

    suspend fun obtenerIndicadores(alumnoId: String): List<Indicador> {
        return supabase.from("indicadores").select {
            filter { eq("idAlumno", alumnoId) }
        }.decodeList()
    }

    suspend fun obtenerNotificaciones(usuarioId: String): List<Notificacion> {
        return supabase.from("notificaciones").select {
            filter { eq("usuarioId", usuarioId) }
            order("fecha", ascending = false)
        }.decodeList()
    }

    suspend fun crearCurso(curso: Curso) {
        supabase.from("cursos").insert(curso)
    }

    suspend fun obtenerCursosPorMaestro(maestroId: String): List<Curso> {
        return supabase.from("cursos").select {
            filter { eq("idMaestro", maestroId) }
        }.decodeList()
    }

    suspend fun inscribirPadreACurso(usuarioId: String, cursoId: String) {
        val usuario = getUsuarioPorId(usuarioId)
        usuario?.let {
            val actualizado = it.copy(cursosInscritos = it.cursosInscritos + cursoId)
            supabase.from("usuarios").update(actualizado) {
                eq("id", usuarioId)
            }
        }
    }
}
