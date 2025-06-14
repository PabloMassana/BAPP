package com.falconteam.bapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.bapp.ui.auth.login.LoginScreen
import com.falconteam.bapp.ui.auth.signup.SignUpScreen
import com.falconteam.bapp.ui.main.HomeScreen
import com.falconteam.bapp.ui.main.chat.ChatScreen
import com.falconteam.bapp.ui.main.evidencias.GalleryScreen
import com.falconteam.bapp.ui.main.indicadores.IndicadoresScreen
import com.falconteam.bapp.ui.main.notifications.NotificationsScreen
import com.falconteam.bapp.ui.main.perfil.ProfileScreen
import com.falconteam.bapp.ui.main.roles.RolSelectScreen
import com.falconteam.bapp.ui.main.tasks.BitacoraScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoutes.Login) {
        composable<NavigationRoutes.Login> { LoginScreen() }
        composable<NavigationRoutes.SignUp> { SignUpScreen() }
        composable<NavigationRoutes.Home> { HomeScreen() }
        composable<NavigationRoutes.Bitacora> { BitacoraScreen() }
        composable<NavigationRoutes.Chat> { ChatScreen() }
        composable<NavigationRoutes.Gallery> { GalleryScreen() }
        composable<NavigationRoutes.Indicadores> { IndicadoresScreen() }
        composable<NavigationRoutes.Profile> { ProfileScreen() }
        composable<NavigationRoutes.Notifications> { NotificationsScreen() }
        composable<NavigationRoutes.RolSelect> { RolSelectScreen() }
    }
}
