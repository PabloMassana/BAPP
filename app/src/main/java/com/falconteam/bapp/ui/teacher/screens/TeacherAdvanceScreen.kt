package com.falconteam.bapp.ui.teacher.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.bapp.ui.teacher.viewmodel.TeacherAdvanceViewModel
import com.falconteam.bapp.ui.theme.BAPPTheme


@Composable

fun TeacherAdvanceScreen(viewModel: TeacherAdvanceViewModel) {


    val grupos = viewModel.grupos
    var grupoSeleccionado by remember { mutableStateOf<String?>(null) }
    var textoBusqueda by remember { mutableStateOf("") }

    val PaleCardColor = Color(0xFFFFD6C9)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFF7F56), Color(0xFFFFB8A9))
                )
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Selector de grupo
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(PaleCardColor, RoundedCornerShape(12.dp))
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            var expanded by remember { mutableStateOf(false) }

            Box {
                TextButton(
                    onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = grupoSeleccionado ?: "Seleccionar grupo")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White)
                ) {
                    grupos.forEach { grupo ->
                        DropdownMenuItem(
                            text = { Text(grupo) },
                            onClick = {
                                grupoSeleccionado = grupo
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Buscador de estudiante
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = { textoBusqueda = it },
            label = { Text("Buscar estudiante") },
            modifier = Modifier.fillMaxWidth(0.9f),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 📝 Aquí irá la lista de estudiantes del grupo seleccionado filtrada por el texto de búsqueda
        // LazyColumn { items(estudiantesFiltrados) { ... } }

        Text(
            text = "Aquí se mostrará la lista de estudiantes",
            color = Color.White.copy(alpha = 0.6f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}



