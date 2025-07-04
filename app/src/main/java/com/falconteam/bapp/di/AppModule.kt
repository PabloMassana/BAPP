package com.falconteam.bapp.di

import androidx.datastore.dataStore
import com.falconteam.bapp.data.local.DataStoreHelper
import com.falconteam.bapp.data.local.createDataStore
import com.falconteam.bapp.data.repository.AdminRepository
import com.falconteam.bapp.data.repository.AdminRepositoryImpl
import com.falconteam.bapp.data.repository.AuthRepository
import com.falconteam.bapp.data.repository.AuthRepositoryImpl
import com.falconteam.bapp.data.repository.MainRepository
import com.falconteam.bapp.data.repository.MainRepositoryImpl
import com.falconteam.bapp.data.repository.TeacherRepository
import com.falconteam.bapp.data.repository.TeacherRepositoryImpl
import com.falconteam.bapp.data.supabase.SupabaseManager
import com.falconteam.bapp.data.supabase.SupabaseManagerImpl
import com.falconteam.bapp.domain.usecases.admin.AprobarDocenteUseCase
import com.falconteam.bapp.domain.usecases.admin.InsertarAlumnoCursoUseCase
import com.falconteam.bapp.domain.usecases.admin.InsertarCursoUseCase
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoAlumnosUseCase
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoCursosUseCase
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoPadresUseCase
import com.falconteam.bapp.domain.usecases.alumno.ObtenerActividadesUseCase
import com.falconteam.bapp.domain.usecases.alumno.ObtenerAlumnoUseCase
import com.falconteam.bapp.domain.usecases.alumno.ObtenerUltimoReporteUseCase
import com.falconteam.bapp.domain.usecases.auth.LoginUseCase
import com.falconteam.bapp.domain.usecases.auth.LogoutUseCase
import com.falconteam.bapp.domain.usecases.auth.ObtenerRolUsuarioUseCase
import com.falconteam.bapp.domain.usecases.auth.ObtenerSesionUsuarioUseCase
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
import com.falconteam.bapp.domain.usecases.teacher.ObtenerActividadesPorMaestroUseCase
import com.falconteam.bapp.domain.usecases.teacher.ObtenerCursosMaestroUseCase
import com.falconteam.bapp.domain.usecases.teacher.ObtenerMaestroUseCase
import com.falconteam.bapp.ui.auth.login.LoginViewModel
import com.falconteam.bapp.ui.auth.signup.SignUpViewModel
import com.falconteam.bapp.ui.main.aprobaradmin.AprobarAdminViewModel
import com.falconteam.bapp.ui.main.borraradmin.DeleteAdminViewModel
import com.falconteam.bapp.ui.main.chat.ChatViewModel
import com.falconteam.bapp.ui.main.childparent.ChildParentViewModel
import com.falconteam.bapp.ui.main.evidencias.GaleriaViewModel
import com.falconteam.bapp.ui.main.homeadmin.HomeAdminViewModel
import com.falconteam.bapp.ui.main.homeparent.HomeParentViewModel
import com.falconteam.bapp.ui.main.hometeacher.HomeTeacherViewModel
import com.falconteam.bapp.ui.main.indicadores.IndicadoresViewModel
import com.falconteam.bapp.ui.main.notifications.NotificacionViewModel
import com.falconteam.bapp.ui.main.perfil.PerfilViewModel
import com.falconteam.bapp.ui.main.tasks.BitacoraViewModel
import com.falconteam.bapp.ui.main.teacheractivity.TeacherActivitiesViewModel
import com.falconteam.bapp.utils.Constants.SUPABASE_API_KEY
import com.falconteam.bapp.utils.Constants.SUPABASE_URL
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        createSupabaseClient(SUPABASE_URL, SUPABASE_API_KEY) {
            install(Auth)
            install(Postgrest)
        }
    }

    single {
        createDataStore(context = get())
    }

    singleOf(::DataStoreHelper)

    single<SupabaseManager> {
        SupabaseManagerImpl(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            supabaseManager = get(),
            dataStoreHelper = get()
        )
    }

    single<AdminRepository> {
        AdminRepositoryImpl(
            supabaseManager = get(),
            dataStoreHelper = get()
        )
    }

    single<TeacherRepository> {
        TeacherRepositoryImpl(
            supabaseManager = get(),
            dataStoreHelper = get()
        )
    }

    single<MainRepository> {
        MainRepositoryImpl(get())
    }

    // Use Cases - Autenticación
    factoryOf(::LoginUseCase)
    factoryOf(::LogoutUseCase)
    factoryOf(::ObtenerRolUsuarioUseCase)
    factoryOf(::SignUpUseCase)
    factoryOf(::ObtenerSesionUsuarioUseCase)

    // Use Cases - Bitácora
    factoryOf(::AgregarBitacoraUseCase)
    factoryOf(::ObtenerBitacorasUseCase)

    // Use Cases - Chat
    factoryOf(::EnviarMensajeChatUseCase)
    factoryOf(::ObtenerMensajesChatUseCase)

    // Use Cases - Evidencias
    factoryOf(::ObtenerEvidenciasUseCase)
    factoryOf(::SubirEvidenciaUseCase)

    // Use Cases - Indicadores
    factoryOf(::ObtenerIndicadoresUseCase)
    factoryOf(::SubirIndicadorUseCase)

    // Use Cases - Notificación
    factoryOf(::ObtenerNotificacionesUseCase)
    factoryOf(::MarcarNotificacionComoLeidaUseCase)

    // Use Cases - Perfil
    factoryOf(::ActualizarRolUseCase)
    factoryOf(::CerrarSesionUseCase)
    factoryOf(::ObtenerRolUsuarioUseCase)

    // HomeParent
    factoryOf(::ObtenerAlumnoUseCase)
    factoryOf(::ObtenerActividadesUseCase)
    factoryOf(::ObtenerUltimoReporteUseCase)

    // Admin
    factoryOf(::ObtenerListadoPadresUseCase)
    factoryOf(::ObtenerListadoCursosUseCase)
    factoryOf(::ObtenerListadoAlumnosUseCase)
    factoryOf(::InsertarCursoUseCase)
    factoryOf(::AprobarDocenteUseCase)
    factoryOf(::InsertarAlumnoCursoUseCase)

    // Teacher
    factoryOf(::ObtenerMaestroUseCase)
    factoryOf(::ObtenerCursosMaestroUseCase)
    factoryOf(::ObtenerActividadesPorMaestroUseCase)

    // ViewModels
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf(::ChildParentViewModel)
    viewModelOf(::ChatViewModel)
    viewModelOf(::GaleriaViewModel)
    viewModelOf(::IndicadoresViewModel)
    viewModelOf(::NotificacionViewModel)
    viewModelOf(::PerfilViewModel)
    viewModelOf(::BitacoraViewModel)
    viewModelOf(::HomeParentViewModel)
    viewModelOf(::HomeAdminViewModel)
    viewModelOf(::DeleteAdminViewModel)
    viewModelOf(::HomeTeacherViewModel)
    viewModelOf(::TeacherActivitiesViewModel)
    viewModelOf(::AprobarAdminViewModel)
}
