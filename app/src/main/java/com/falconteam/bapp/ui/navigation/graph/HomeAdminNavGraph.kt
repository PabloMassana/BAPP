package com.falconteam.bapp.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.bapp.ui.main.aprobaradmin.AprobarAdminScreen
import com.falconteam.bapp.ui.main.borraradmin.BorrarAdminScreen
import com.falconteam.bapp.ui.main.homeadmin.HomeAdminScreen
import com.falconteam.bapp.ui.navigation.NavigationRoutes

@Composable
fun HomeAdminNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavigationRoutes.HomeAdminDestination.HomeAdmin
    ) {
        composable<NavigationRoutes.HomeAdminDestination.HomeAdmin> {
            // Home Admin Screen
            HomeAdminScreen()
        }
        composable<NavigationRoutes.HomeAdminDestination.DeleteAdmin> {
            BorrarAdminScreen(
                cursos = emptyList(),
                estudiantes = emptyList()
            ) { }
        }
        composable<NavigationRoutes.HomeAdminDestination.TeachersManagementAdmin> {
            AprobarAdminScreen()
        }
    }
}