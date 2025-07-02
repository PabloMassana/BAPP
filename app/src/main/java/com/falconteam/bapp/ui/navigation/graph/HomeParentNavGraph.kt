package com.falconteam.bapp.ui.navigation.graph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.falconteam.bapp.ui.main.chat.ChatScreen
import com.falconteam.bapp.ui.main.childparent.MyChildrenScreen
import com.falconteam.bapp.ui.main.homeparent.HomeParentScreen
import com.falconteam.bapp.ui.navigation.NavigationRoutes

@Composable
fun HomeParentNavGraph(
    modifier: Modifier = Modifier,
    rootNavController: NavController,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoutes.HomeParentDestination.HomeParent
    ) {
        composable<NavigationRoutes.HomeParentDestination.HomeParent> {
            HomeParentScreen()
        }
        composable<NavigationRoutes.HomeParentDestination.MyChildren> {
            MyChildrenScreen()
        }
        composable<NavigationRoutes.Chat> {
            ChatScreen()
        }
        composable<NavigationRoutes.HomeParentDestination.ReportsParent> {
            Text(text = "Reoports Parent") // Placeholder for ReportsParent screen
        }
        composable<NavigationRoutes.Profile> {
            Text(text = "Profile") // Placeholder for Profile screen
        }
    }
}