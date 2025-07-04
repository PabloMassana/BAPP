package com.falconteam.bapp.ui.main.homeadmin

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminBaseScreenContent(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selectedItem by remember { mutableStateOf(BottomAdminNavItem.ADD) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

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
        },
        floatingActionButton = {
            if (selectedItem == BottomAdminNavItem.ADD) {
                FloatingActionButton(
                    onClick = {
                        showBottomSheet = true
                    },
                    containerColor = Color(0xFFFFA07A),
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Agregar"
                    )
                }
            }
        }
    ) {
        HomeAdminNavGraph(
            modifier = modifier.padding(it),
            navHostController = navController
        )

        if (showBottomSheet) {
            HomeAdminBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,

            )
        }
    }
}