package com.falconteam.bapp.ui.components.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R
import com.falconteam.bapp.data.models.Notificacion
import com.falconteam.bapp.domain.usecases.auth.LogoutUseCase
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BappTopBar(
    title: String,
    notifications: List<Notificacion> = emptyList(),
    isAdmin: Boolean = false,
    onNavigate: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val unreadCount = notifications.count { !it.leida }
    var expandedProfileMenu by remember { mutableStateOf(false) }
    val logoutUseCase = koinInject<LogoutUseCase>()

    TopAppBar(
        title = {
            Text(text = title)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFA07A), // Color salmón
            titleContentColor = Color.Black
        ),
        actions = {
            if (!isAdmin) {
                Box(contentAlignment = Alignment.TopEnd) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentDescription = "Notificaciones",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                    if (unreadCount > 0) {
                        Text(
                            text = unreadCount.toString(),
                            color = Color.Black,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .offset(x = 12.dp, y = (-6).dp)
                                .background(Color.White, shape = MaterialTheme.shapes.small)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(Modifier.width(16.dp))
            }

            Box(modifier = Modifier.clickable { expandedProfileMenu = true }) {
                Image(
                    painter = painterResource(id = R.drawable.parent), // Imagen del usuario
                    contentDescription = "Perfil",
                    modifier = Modifier.size(36.dp)
                )
                DropdownMenu(
                    expanded = expandedProfileMenu,
                    onDismissRequest = { expandedProfileMenu = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Cerrar sesión") },
                        onClick = {
                            coroutineScope.launch {
                                logoutUseCase()
                                    .onSuccess {
                                        onNavigate()
                                    }
                                    .onFailure {
                                        println("error al cerrar sesión: ${it.message}")
                                    }
                            }
                        }
                    )
                }
            }
        }
    )
}