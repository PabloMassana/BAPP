package com.falconteam.bapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.bapp.ui.main.HomeFragment
import com.falconteam.bapp.ui.auth.LoginActivity
import com.falconteam.bapp.ui.auth.RegisterActivity
import com.falconteam.bapp.ui.main.chat.ChatFragment
import com.falconteam.bapp.ui.main.chat.UrgenteFragment
import com.falconteam.bapp.ui.main.evidencias.GaleriaFragment
import com.falconteam.bapp.ui.main.indicadores.IndicadoresFragment
import com.falconteam.bapp.ui.main.notifications.NotificacionesFragment
import com.falconteam.bapp.ui.main.perfil.PerfilFragment
import com.falconteam.bapp.ui.main.roles.SeleccionRolFragment
import com.falconteam.bapp.ui.main.tasks.BitacoraFragment

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoutes.LOGIN) {
        composable(NavigationRoutes.LOGIN) { LoginActivity() }
        composable(NavigationRoutes.REGISTER) { RegisterActivity() }
        composable(NavigationRoutes.HOME) { HomeFragment() }
        composable(NavigationRoutes.BITACORA) { BitacoraFragment() }
        composable(NavigationRoutes.CHAT) { ChatFragment() }
        composable(NavigationRoutes.URGENTE) { UrgenteFragment() }
        composable(NavigationRoutes.GALERIA) { GaleriaFragment() }
        composable(NavigationRoutes.INDICADORES) { IndicadoresFragment() }
        composable(NavigationRoutes.NOTIFICACIONES) { NotificacionesFragment() }
        composable(NavigationRoutes.PERFIL) { PerfilFragment() }
        composable(NavigationRoutes.SELECCION_ROL) { SeleccionRolFragment() }
    }
}
