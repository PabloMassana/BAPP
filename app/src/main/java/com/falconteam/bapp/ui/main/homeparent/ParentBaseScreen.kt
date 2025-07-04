package com.falconteam.bapp.ui.main.homeparent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.falconteam.bapp.ui.components.appbar.BappNavigationBar
import com.falconteam.bapp.ui.components.appbar.BappTopBar
import com.falconteam.bapp.ui.navigation.graph.HomeParentNavGraph

@Composable
fun ParentBaseScreen(
    rootNavController: NavHostController,
) {
    ParentBaseScreenContent(rootNavController = rootNavController)
}

@Composable
fun ParentBaseScreenContent(modifier: Modifier = Modifier, rootNavController: NavHostController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selectedItem by remember { mutableStateOf(BottomParentNavItem.HOME) }

    Scaffold(
        topBar = {
            BappTopBar(title = selectedItem.label)
        },
        bottomBar = {
            BappNavigationBar(
                navItems = BottomParentNavItem.entries,
                currentDestination = currentDestination,
                navController = navController
            ) {
                selectedItem = it
            }
        }
    ) { pv ->
        HomeParentNavGraph(
            modifier = modifier.padding(pv),
            rootNavController = rootNavController,
            navController = navController
        )
    }
}
