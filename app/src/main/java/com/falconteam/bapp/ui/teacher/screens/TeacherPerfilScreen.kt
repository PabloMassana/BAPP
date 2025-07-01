package com.falconteam.bapp.ui.teacher.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.falconteam.bapp.ui.theme.BAPPTheme

@Composable
fun TeacherPerfilScreen() {
    val backgroundColor = Brush.verticalGradient(
        colors = listOf(Color(0xFFFF7F56), Color(0xFFFFB8A9))
    )

    val cardColor = Color(0xFFFFD6C9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        // ✅ Perfil del maestro
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 📸 Foto del maestro (placeholder circular)
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("👤", fontSize = 28.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                // 📝 Información
                Column {
                    Text("Nombre: Juan Pérez", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text("Correo: juan.perez@escuela.com", fontSize = 14.sp)
                    Text("Rol: Docente", fontSize = 14.sp)
                }
            }
        }

        // ✅ Grupo y lista de estudiantes
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Kinder 4 seccion A", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Estudiantes:", fontSize = 14.sp, fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.height(8.dp))

                val estudiantes = listOf(
                    "Ana Martínez",
                    "Carlos Ramírez",
                    "Daniela López",
                    "Fernanda Ruiz"
                )

                estudiantes.forEach { estudiante ->
                    Text("• $estudiante", fontSize = 13.sp)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TeacherPerfilScreenPreview() {
    BAPPTheme {
        TeacherPerfilScreen()
    }
}
