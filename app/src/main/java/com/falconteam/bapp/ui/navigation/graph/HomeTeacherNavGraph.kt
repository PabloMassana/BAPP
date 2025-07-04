package com.falconteam.bapp.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.bapp.ui.main.teacheractivity.TeacherActivitiesScreen
import com.falconteam.bapp.ui.main.avancesteacher.AvancesTeacherScreen
import com.falconteam.bapp.ui.main.chat.ChatScreen
import com.falconteam.bapp.ui.main.hometeacher.HomeTeacherScreen
import com.falconteam.bapp.ui.main.perfil.ProfileScreen
import com.falconteam.bapp.ui.navigation.NavigationRoutes

@Composable
fun HomeTeacherNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = NavigationRoutes.HomeTeacherDestination.HomeTeacher
    ) {
        composable<NavigationRoutes.HomeTeacherDestination.HomeTeacher> {
            HomeTeacherScreen()
        }
        composable<NavigationRoutes.HomeTeacherDestination.ReportsTeacher> {
            AvancesTeacherScreen()
        }
        composable<NavigationRoutes.HomeTeacherDestination.AddActivities> {
            TeacherActivitiesScreen()
        }
        composable<NavigationRoutes.Chat> {
            ChatScreen()
        }
        composable<NavigationRoutes.Profile> {
            ProfileScreen()
        }
    }
}
