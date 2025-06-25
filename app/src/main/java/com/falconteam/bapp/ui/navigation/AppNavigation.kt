package com.falconteam.bapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.bapp.ui.auth.login.LoginScreen
import com.falconteam.bapp.ui.auth.signup.SignUpScreen
import com.falconteam.bapp.ui.main.homeparent.HomeParentScreen
import com.falconteam.bapp.ui.main.perfil.ProfileScreen
import com.falconteam.bapp.ui.main.roles.RolSelectScreen
import com.falconteam.bapp.ui.main.tasks.BitacoraScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoutes.Login) {
        composable<NavigationRoutes.Login> {
            LoginScreen {
                navController.navigate(NavigationRoutes.SignUp)
            }
        }
        composable<NavigationRoutes.SignUp> {
            SignUpScreen {
                navController.navigate(NavigationRoutes.Login)
            }
        }
        composable<NavigationRoutes.Home> {
            HomeParentScreen()
        }
        composable<NavigationRoutes.Bitacora> {
            BitacoraScreen()
        }
        composable<NavigationRoutes.Profile> {
            ProfileScreen()
        }
        composable<NavigationRoutes.RolSelect> {
            RolSelectScreen()
        }
    }
}
