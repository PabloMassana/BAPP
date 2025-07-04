package com.falconteam.bapp.ui.main.chatparent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R

data class UsuarioMock(
    val nombreCompleto: String,
    val avatarResId: Int,
    val mensajesNoLeidos: Int = 0
)

@Composable
fun ChatParentScreen() {
    val noLeidos = listOf(
        UsuarioMock("Juanito Alejandro López", R.drawable.parent, 2),
        UsuarioMock("Sofía Martínez López", R.drawable.parent, 1),
        UsuarioMock("Mateo Ramírez Torres", R.drawable.parent, 3)
    )
    val disponibles = listOf(
        UsuarioMock("Juanito Alejandro López", R.drawable.parent),
        UsuarioMock("Sofía Martínez López", R.drawable.parent)
    )

    var nombreFiltro by remember { mutableStateOf(TextFieldValue("")) }
    var apellidoFiltro by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        Text("Filtrar por:", style = MaterialTheme.typography.labelLarge)

        Spacer(Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = nombreFiltro,
                onValueChange = { nombreFiltro = it },
                placeholder = { Text("Nombre...") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )
            OutlinedTextField(
                value = apellidoFiltro,
                onValueChange = { apellidoFiltro = it },
                placeholder = { Text("Apellido...") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )
        }

        Spacer(Modifier.height(24.dp))
        Text("No leídos", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(noLeidos) { usuario ->
                UsuarioConBadge(usuario)
            }
        }

        Spacer(Modifier.height(24.dp))
        Text("Lista de docentes disponibles", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            disponibles.forEach { usuario ->
                UsuarioDisponible(usuario)
            }
        }
    }
}

@Composable
fun UsuarioConBadge(usuario: UsuarioMock) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = usuario.avatarResId),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.LightGray, CircleShape)
            )
            Spacer(Modifier.width(12.dp))
            Text(usuario.nombreCompleto, fontSize = 15.sp)
        }

        if (usuario.mensajesNoLeidos > 0) {
            Surface(
                shape = CircleShape,
                color = Color(0xFFD32F2F), // Rojo intenso
                modifier = Modifier.size(22.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = usuario.mensajesNoLeidos.toString(),
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun UsuarioDisponible(usuario: UsuarioMock) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(usuario.nombreCompleto, modifier = Modifier.weight(1f), fontSize = 15.sp)
        Image(
            painter = painterResource(id = usuario.avatarResId),
            contentDescription = "Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(36.dp)
                .background(Color.LightGray, CircleShape)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChatParentScreenPreview() {
    ChatParentScreen()
}
