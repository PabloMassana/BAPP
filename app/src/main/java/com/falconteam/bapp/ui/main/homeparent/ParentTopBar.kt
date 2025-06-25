package com.falconteam.bapp.ui.main.homeparent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R
import com.falconteam.bapp.data.models.Notificacion

@Composable
fun ParentTopBar(
    notifications: List<Notificacion>,
    modifier: Modifier = Modifier
) {
    val unreadCount = notifications.count { !it.leida }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFFFA07A)) // Color salmón
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Campana de notificación
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

        // Espaciador para centrar el avatar a la derecha
        Spacer(modifier = Modifier.weight(1f))

        // Avatar del usuario
        Image(
            painter = painterResource(id = R.drawable.parent), // Imagen del usuario
            contentDescription = "Perfil",
            modifier = Modifier
                .size(36.dp)
        )
    }
}

