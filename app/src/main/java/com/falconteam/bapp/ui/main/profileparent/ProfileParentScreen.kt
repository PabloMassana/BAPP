package com.falconteam.bapp.ui.main.profileparent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R

@Composable
fun ProfileParentScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        ProfileHeader(
            name = "María González",
            email = "marigonz@gmail.com",
            role = "Padre/madre de familia",
            avatarRes = R.drawable.parent
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Tus hijos", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(8.dp))

        val hijos = listOf(
            "Juanito Alejandro López" to R.drawable.hijo,
            "Sofía Martínez López" to R.drawable.hijo,
            "Mateo Ramírez Torres" to R.drawable.hijo
        )

        hijos.forEach {
            ProfileListItem(name = it.first, avatarRes = it.second)
            Spacer(modifier = Modifier.height(6.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Docentes:", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(8.dp))

        val docentes = listOf(
            "Juanito Alejandro López" to R.drawable.parent,
            "Sofía Martínez López" to R.drawable.parent,
            "Mateo Ramírez Torres" to R.drawable.parent
        )

        docentes.forEach {
            ProfileListItem(name = it.first, avatarRes = it.second)
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Composable
fun ProfileHeader(name: String, email: String, role: String, avatarRes: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.7f) // transparencia
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = Color.LightGray
            ) {
                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = "Avatar",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "Correo: $email", fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(text = "Rol: $role", fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun ProfileListItem(name: String, avatarRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            modifier = Modifier.size(32.dp),
            shape = CircleShape,
            color = Color.LightGray // da contraste si la imagen no es circular
        ) {
            Image(
                painter = painterResource(id = avatarRes),
                contentDescription = "Avatar",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileParentScreenPreview() {
    ProfileParentScreen()
}
