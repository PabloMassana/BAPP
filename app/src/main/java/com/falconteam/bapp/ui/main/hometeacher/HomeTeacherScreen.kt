package com.falconteam.bapp.ui.main.hometeacher

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.ui.main.hometeacher.components.ActivityCard
import com.falconteam.bapp.ui.main.hometeacher.components.GroupCard
import com.falconteam.bapp.ui.main.hometeacher.components.TeacherHeader
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeTeacherScreen(
    viewModel: HomeTeacherViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.errorMessage != null -> {
                Text(
                    text = "Error: ${state.errorMessage}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                Column(modifier = Modifier.fillMaxSize()) {
                    TeacherHeader(name = state.teacherName, avatarUrl = null)

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Últimas actividades registradas",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(state.actividades) { actividad ->
                            ActivityCard(
                                title = actividad.titulo,
                                time = actividad.hora.toString(),
                                imageUrl = actividad.imagenUrl
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Tus grupos",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    if (state.cursos.isEmpty()) {
                        Text(
                            text = "No se encontraron grupos",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    } else {
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            items(state.cursos) { curso ->
                                GroupCard(
                                    groupName = curso.nombre,
                                    modifier = Modifier.width(220.dp)
                                )
                            }
                        }
                    }
                }

                FloatingActionButton(
                    onClick = { /* TODO */ },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                    shape = CircleShape,
                    containerColor = Color(0xFFFF8A65)
                ) {
                    Text("+", fontSize = 22.sp, color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTeacherScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TeacherHeader(name = "Brandon Cornejo", avatarUrl = null)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Últimas actividades registradas",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(listOf("Dibujar", "Manualidades")) { titulo ->
                    ActivityCard(
                        title = titulo,
                        time = "8:00 am",
                        imageUrl = "https://placekitten.com/400/400"
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Tus grupos",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(listOf("Kínder 4 - A", "Kínder 4 - B")) { grupo ->
                    GroupCard(
                        groupName = grupo,
                        modifier = Modifier.width(220.dp)
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
            shape = CircleShape,
            containerColor = Color(0xFFFF8A65)
        ) {
            Text("+", fontSize = 22.sp, color = Color.White)
        }
    }
}
