package com.tuapp.di

import com.falconteam.bapp.data.repository.SupabaseRepository
import com.falconteam.bapp.data.supabase.SupabaseService
import com.falconteam.bapp.domain.usecases.auth.IniciarSesionUseCase
import com.falconteam.bapp.domain.usecases.auth.ObtenerRolUsuarioUseCase
import com.falconteam.bapp.domain.usecases.bitacora.AgregarBitacoraUseCase
import com.falconteam.bapp.domain.usecases.bitacora.ObtenerBitacorasUseCase
import com.falconteam.bapp.domain.usecases.chat.EnviarMensajeChatUseCase
import com.falconteam.bapp.domain.usecases.chat.ObtenerMensajesChatUseCase
import com.falconteam.bapp.domain.usecases.evidencia.ObtenerEvidenciasUseCase
import com.falconteam.bapp.domain.usecases.evidencia.SubirEvidenciaUseCase
import com.falconteam.bapp.domain.usecases.indicador.ObtenerIndicadoresUseCase
import com.falconteam.bapp.domain.usecases.notificacion.ObtenerNotificacionesUseCase
import org.koin.dsl.module

val appModule = module {

    // Cliente Supabase configurado correctamente
    single { SupabaseService.client }

    // Repositorio de datos
    single { SupabaseRepository(get()) }

    // Use Cases - Autenticación
    factory { IniciarSesionUseCase(get()) }
    factory { ObtenerRolUsuarioUseCase(get()) }

    // Use Cases - Bitácora
    factory { AgregarBitacoraUseCase(get()) }
    factory { ObtenerBitacorasUseCase(get()) }

    // Use Cases - Chat
    factory { EnviarMensajeChatUseCase(get()) }
    factory { ObtenerMensajesChatUseCase(get()) }

    // Use Cases - Evidencias
    factory { ObtenerEvidenciasUseCase(get()) }
    factory { SubirEvidenciaUseCase(get()) }

    // Use Cases - Indicadores
    factory { ObtenerIndicadoresUseCase(get()) }

    // Use Cases - Notificaciones
    factory { ObtenerNotificacionesUseCase(get()) }

    // Puedes agregar aquí los nuevos casos de uso si lo necesitas para Curso o Usuarios
}
