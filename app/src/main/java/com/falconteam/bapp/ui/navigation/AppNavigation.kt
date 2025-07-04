package com.falconteam.bapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.bapp.ui.auth.login.LoginScreen
import com.falconteam.bapp.ui.auth.signup.SignUpScreen
import com.falconteam.bapp.ui.main.homeadmin.AdminBaseScreen
import com.falconteam.bapp.ui.main.homeparent.HomeParentScreen
import com.falconteam.bapp.ui.main.homeparent.ParentBaseScreen
import com.falconteam.bapp.ui.main.hometeacher.TeacherBaseScreen
import com.falconteam.bapp.ui.main.perfil.ProfileScreen
import com.falconteam.bapp.ui.main.roles.RolSelectScreen
import com.falconteam.bapp.ui.main.tasks.BitacoraScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationRoutes.Login) {
        composable<NavigationRoutes.Login> {
            LoginScreen { destination ->
                navController.navigate(destination)
            }
        }
        composable<NavigationRoutes.SignUp> {
            SignUpScreen {
                navController.navigate(NavigationRoutes.Login)
            }
        }
        composable<NavigationRoutes.HomeTeacherDestination.HomeTeacherNavGraph> {
            TeacherBaseScreen(
                rootNavController = navController
            )
        }
        composable<NavigationRoutes.HomeParentDestination.HomeParentNavGraph> {
            ParentBaseScreen(
                rootNavController = navController
            )
        }
        composable<NavigationRoutes.HomeAdminDestination.HomeAdminNavGraph> {
            AdminBaseScreen(
                rootNavController = navController
            )
        }
    }
}
