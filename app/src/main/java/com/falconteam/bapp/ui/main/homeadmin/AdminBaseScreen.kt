package com.falconteam.bapp.ui.main.homeadmin

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.falconteam.bapp.ui.components.appbar.BappNavigationBar
import com.falconteam.bapp.ui.components.appbar.BappTopBar
import com.falconteam.bapp.ui.navigation.NavigationRoutes
import com.falconteam.bapp.ui.navigation.graph.HomeAdminNavGraph

@Composable
fun AdminBaseScreen(
    rootNavController: NavHostController
) {
    AdminBaseScreenContent(rootNavController = rootNavController)
}

@Composable
fun AdminBaseScreenContent(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selectedItem by remember { mutableStateOf(BottomAdminNavItem.ADD) }

    Scaffold(
        topBar = {
            BappTopBar(title = selectedItem.label, isAdmin = true) {
                rootNavController.navigate(NavigationRoutes.Login)
            }
        },
        bottomBar = {
            BappNavigationBar(
                navItems = BottomAdminNavItem.entries,
                currentDestination = currentDestination,
                navController = navController
            ) {
                selectedItem = it
            }
        }
    ) {
        HomeAdminNavGraph(
            modifier = modifier.padding(it),
            navHostController = navController
        )
    }
}