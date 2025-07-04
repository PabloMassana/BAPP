package com.falconteam.bapp.ui.main.borraradmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.bapp.R

data class Estudiante(
    val nombre: String,
    val avatarRes: Int
)

@Composable
fun BorrarAdminScreen(
    cursos: List<String>,
    estudiantes: List<Estudiante>,
    onEliminarClick: (Estudiante) -> Unit
) {
    var cursoSeleccionado by remember { mutableStateOf(cursos.firstOrNull() ?: "") }
    var expanded by remember { mutableStateOf(false) }
    var filtroTexto by remember { mutableStateOf("") }

    val estudiantesFiltrados = estudiantes.filter {
        it.nombre.contains(filtroTexto, ignoreCase = true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        Column {
            // Selector de curso
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .clickable { expanded = true }
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = cursoSeleccionado)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Expandir"
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                cursos.forEach { curso ->
                    DropdownMenuItem(
                        text = { Text(curso) },
                        onClick = {
                            cursoSeleccionado = curso
                            expanded = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp)) // Espacio entre filtros

            // Campo de búsqueda
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    BasicTextField(
                        value = filtroTexto,
                        onValueChange = { filtroTexto = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 12.dp),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            if (filtroTexto.isEmpty()) {
                                Text("Buscar...", color = Color.Gray)
                            }
                            innerTextField()
                        }
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Buscar"
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Lista de estudiantes
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(estudiantesFiltrados) { estudiante ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = estudiante.avatarRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = estudiante.nombre,
                                modifier = Modifier.weight(1f)
                            )

                            IconButton(onClick = { onEliminarClick(estudiante) }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_trash),
                                    contentDescription = "Eliminar"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BorrarAdminScreenPreview() {
    val cursos = listOf("Kinder 4 - Sección A", "Kinder 4 - Sección B")
    val estudiantes = listOf(
        Estudiante("Juanito Alejandro López", R.drawable.parent),
        Estudiante("Sofía Martínez López", R.drawable.parent),
        Estudiante("Mateo Ramírez Torres", R.drawable.parent)
    )
    BorrarAdminScreen(cursos = cursos, estudiantes = estudiantes, onEliminarClick = {})
}
