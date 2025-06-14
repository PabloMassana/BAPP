package com.tuapp.di

import GaleriaViewModel
import com.falconteam.bapp.data.repository.AuthRepository
import com.falconteam.bapp.data.repository.AuthRepositoryImpl
import com.falconteam.bapp.data.repository.MainRepository
import com.falconteam.bapp.data.repository.MainRepositoryImpl
import com.falconteam.bapp.data.supabase.SupabaseManager
import com.falconteam.bapp.data.supabase.SupabaseManagerImpl
import com.falconteam.bapp.domain.usecases.auth.LoginUseCase
import com.falconteam.bapp.domain.usecases.auth.ObtenerRolUsuarioUseCase
import com.falconteam.bapp.domain.usecases.auth.SignUpUseCase
import com.falconteam.bapp.domain.usecases.bitacora.AgregarBitacoraUseCase
import com.falconteam.bapp.domain.usecases.bitacora.ObtenerBitacorasUseCase
import com.falconteam.bapp.domain.usecases.chat.EnviarMensajeChatUseCase
import com.falconteam.bapp.domain.usecases.chat.ObtenerMensajesChatUseCase
import com.falconteam.bapp.domain.usecases.evidencia.ObtenerEvidenciasUseCase
import com.falconteam.bapp.domain.usecases.evidencia.SubirEvidenciaUseCase
import com.falconteam.bapp.domain.usecases.indicador.ObtenerIndicadoresUseCase
import com.falconteam.bapp.domain.usecases.indicador.SubirIndicadorUseCase
import com.falconteam.bapp.domain.usecases.notificacion.MarcarNotificacionComoLeidaUseCase
import com.falconteam.bapp.domain.usecases.notificacion.ObtenerNotificacionesUseCase
import com.falconteam.bapp.domain.usecases.perfil.ActualizarRolUseCase
import com.falconteam.bapp.domain.usecases.perfil.CerrarSesionUseCase
import com.falconteam.bapp.ui.auth.login.LoginViewModel
import com.falconteam.bapp.ui.auth.signup.SignUpViewModel
import com.falconteam.bapp.ui.main.chat.ChatViewModel
import com.falconteam.bapp.ui.main.indicadores.IndicadoresViewModel
import com.falconteam.bapp.ui.main.notifications.NotificacionViewModel
import com.falconteam.bapp.ui.main.perfil.PerfilViewModel
import com.falconteam.bapp.ui.main.tasks.BitacoraViewModel
import com.falconteam.bapp.utils.Constants.SUPABASE_API_KEY
import com.falconteam.bapp.utils.Constants.SUPABASE_URL
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        createSupabaseClient(SUPABASE_URL, SUPABASE_API_KEY) {
            install(Auth)
            install(Postgrest)
        }
    }

    single<SupabaseManager> {
        SupabaseManagerImpl(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

    single<MainRepository> {
        MainRepositoryImpl(get())
    }

    // Use Cases - Autenticación
    factoryOf(::LoginUseCase)
    factoryOf(::ObtenerRolUsuarioUseCase)
    factoryOf(::SignUpUseCase)

    // Use Cases - Bitácora
    factoryOf(:: AgregarBitacoraUseCase)
    factoryOf(:: ObtenerBitacorasUseCase)

    // Use Cases - Chat
    factoryOf(::EnviarMensajeChatUseCase)
    factoryOf(::ObtenerMensajesChatUseCase)

    // Use Cases - Evidencias
    factoryOf(:: ObtenerEvidenciasUseCase)
    factoryOf(:: SubirEvidenciaUseCase)

    // Use Cases - Indicadores
    factoryOf(:: ObtenerIndicadoresUseCase)
    factoryOf(:: SubirIndicadorUseCase)

    // Use Cases - Notificación
    factoryOf(:: ObtenerNotificacionesUseCase)
    factoryOf(:: MarcarNotificacionComoLeidaUseCase)

    // Use Cases - Perfil
    factoryOf(:: ActualizarRolUseCase)
    factoryOf(:: CerrarSesionUseCase)
    factoryOf(:: ObtenerRolUsuarioUseCase)

    // ViewModels
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(:: ChatViewModel)
    viewModelOf(:: GaleriaViewModel)
    viewModelOf(:: IndicadoresViewModel)
    viewModelOf(:: NotificacionViewModel)
    viewModelOf(:: PerfilViewModel)
    viewModelOf(:: BitacoraViewModel)
}
