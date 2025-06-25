package com.falconteam.bapp.ui.main.chatparent

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.falconteam.bapp.R
import com.falconteam.bapp.data.models.Rol
import com.falconteam.bapp.data.models.Usuario

@Composable
fun ChatParentScreen(
    currentUserId: String,
    navController: NavController,
    docentesDisponibles: List<Usuario>,
    mensajesNoLeidos: Map<String, Int>
) {
    var filtroNombre by remember { mutableStateOf("") }
    var filtroApellido by remember { mutableStateOf("") }

    val docentesFiltrados = docentesDisponibles.filter {
        it.nombre.contains(filtroNombre, ignoreCase = true) &&
                it.nombre.substringAfterLast(" ").contains(filtroApellido, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFA07A), Color(0xFFFFFBF9))
                )
            )
            .padding(16.dp)
    ) {
        Text("Filtrar por:", fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = filtroNombre,
                onValueChange = { filtroNombre = it },
                label = { Text("Nombre...") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = filtroApellido,
                onValueChange = { filtroApellido = it },
                label = { Text("Apellido...") },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(Modifier.height(24.dp))
        Text("No leídos", fontWeight = FontWeight.SemiBold)

        mensajesNoLeidos.keys.mapNotNull { id ->
            docentesDisponibles.find { it.id == id }
        }.forEach { docente ->
            DocenteChatItem(
                usuario = docente,
                unreadCount = mensajesNoLeidos[docente.id] ?: 0,
                onClick = {
                    val conversacionId = generarConversacionId(currentUserId, docente.id)
                    navController.navigate("chat_screen/$conversacionId/$currentUserId")
                }
            )
        }

        Spacer(Modifier.height(16.dp))
        Text("Lista de docentes disponibles", fontWeight = FontWeight.SemiBold)

        docentesFiltrados.forEach { docente ->
            DocenteChatItem(
                usuario = docente,
                onClick = {
                    val conversacionId = generarConversacionId(currentUserId, docente.id)
                    navController.navigate("chat_screen/$conversacionId/$currentUserId")
                }
            )
        }
    }
}

@Composable
fun DocenteChatItem(
    usuario: Usuario,
    unreadCount: Int = 0,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.parent), // Usa avatar diferente si aplica
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        Spacer(Modifier.width(12.dp))

        Text(
            text = usuario.nombre,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )

        if (unreadCount > 0) {
            Badge(
                containerColor = Color(0xFFE57373),
                contentColor = Color.White
            ) {
                Text("$unreadCount")
            }
        }
    }
}

// Utilidad para generar conversación única entre 2 usuarios
fun generarConversacionId(user1: String, user2: String): String {
    return if (user1 < user2) "${user1}_$user2" else "${user2}_$user1"
}

@Preview(showBackground = true)
@Composable
fun ChatParentScreenPreview() {
    val docentesEjemplo = listOf(
        Usuario(
            id = "1",
            nombre = "Juanito Alejandro López",
            email = "juanito@escuela.com",
            rol = Rol.MAESTRO,
            cursosInscritos = listOf("Kinder-A")
        ),
        Usuario(
            id = "2",
            nombre = "Sofía Martínez López",
            email = "sofia@escuela.com",
            rol = Rol.MAESTRO,
            cursosInscritos = listOf("Kinder-A")
        ),
        Usuario(
            id = "3",
            nombre = "Mateo Ramírez Torres",
            email = "mateo@escuela.com",
            rol = Rol.MAESTRO,
            cursosInscritos = listOf("Kinder-B")
        )
    )

    val mensajesNoLeidosEjemplo = mapOf(
        "1" to 2,
        "2" to 1,
        "3" to 3
    )

    val navController = rememberNavController()

    ChatParentScreen(
        currentUserId = "padre_123",
        navController = navController,
        docentesDisponibles = docentesEjemplo,
        mensajesNoLeidos = mensajesNoLeidosEjemplo
    )
}

