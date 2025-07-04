package com.falconteam.bapp.ui.components.appbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.falconteam.bapp.ui.navigation.navitems.BottomNavItem
import kotlin.enums.EnumEntries

@Composable
fun <T> BappNavigationBar(
    navItems: EnumEntries<T>,
    currentDestination: NavDestination?,
    navController: NavController,
    onClickNavItem: (T) -> Unit
) where T: Enum<T>, T: BottomNavItem {
    NavigationBar {
        navItems.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { navDestination ->
                navDestination.hasRoute(item.destination::class)
            }

            NavigationBarItem(
                selected = selected == true,
                onClick = {
                    onClickNavItem(item)
                    navController.navigate(item.destination) {
                        popUpTo(navController.graph.findStartDestination().route ?: "") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFFA07A),
                    unselectedIconColor = Color(0xFF484C52),
                    selectedTextColor = Color(0xFFFFA07A),
                    unselectedTextColor = Color(0xFF484C52),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

