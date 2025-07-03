package com.falconteam.bapp.ui.main.hometeacher

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.ui.components.appbar.BappNavigationBar
import com.falconteam.bapp.ui.components.appbar.BappTopBar
import com.falconteam.bapp.ui.main.teacheractivity.EditActividadDialog
import com.falconteam.bapp.ui.navigation.NavigationRoutes
import com.falconteam.bapp.ui.navigation.graph.HomeTeacherNavGraph
import java.sql.Time
import java.util.Date

@Composable
fun TeacherBaseScreen(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var selectedItem by remember { mutableStateOf(BottomTeacherNavItem.HOME) }

    // Estado para mostrar el dialogo de agregar actividad
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            BappTopBar(
                title = selectedItem.label,
                isAdmin = false,  // Siempre es false para Teacher
            ) {
                rootNavController.navigate(NavigationRoutes.Login)
            }
        },
        bottomBar = {
            BappNavigationBar(
                navItems = BottomTeacherNavItem.entries,
                currentDestination = currentDestination,
                navController = navController
            ) {
                selectedItem = it
            }
        },
        floatingActionButton = {
            if (selectedItem == BottomTeacherNavItem.HOME || selectedItem == BottomTeacherNavItem.ADD)
                FloatingActionButton(
                    onClick = { showDialog = true }, // Al hacer click en el FAB, abrimos el dialogo
                    modifier = Modifier.padding(16.dp),
                    shape = CircleShape,
                    containerColor = Color(0xFFFF8A65)
                ) {
                    Text("+", fontSize = 22.sp, color = Color.White)
                }
        }
    ) {
        // Mostrar el dialogo para agregar una nueva actividad
        if (showDialog) {
            EditActividadDialog(
                actividad = Actividad(
                    id = 0,  // nueva actividad
                    fecha = Date(),
                    titulo = "",
                    hora = Time(System.currentTimeMillis()),
                    imagenUrl = "",
                    cursoId = 1
                ),
                onDismiss = { showDialog = false }
            )
        }

        // Aquí va tu HomeTeacherNavGraph u otros contenidos de la pantalla
        HomeTeacherNavGraph(
            modifier = modifier.padding(it),
            navHostController = navController
        )
    }
}
