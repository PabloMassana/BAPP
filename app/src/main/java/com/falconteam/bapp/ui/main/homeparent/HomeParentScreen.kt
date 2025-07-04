
package com.falconteam.bapp.ui.main.homeparent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R
import com.falconteam.bapp.data.models.Alumno
import com.falconteam.bapp.data.models.Reporte
import com.falconteam.bapp.ui.main.hometeacher.components.ActivityCard
import com.falconteam.bapp.ui.theme.BAPPTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeParentScreen(
    viewModel: HomeParentViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        uiState.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${uiState.error}")
            }
        }

        uiState.alumno != null -> {
            ParentHomeContent(
                hijo = uiState.alumno!!,
                actividades = uiState.actividades,
                ultimoReporte = uiState.ultimoReporte
            )
        }
    }
}

@Composable
fun ParentHomeContent(
    hijo: Alumno,
    actividades: List<Actividad> = emptyList(),
    ultimoReporte: Reporte? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        // Tarjeta de bienvenida
        Card(
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD3BD)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("¡Bienvenido!, encargado de", fontSize = 14.sp)
                Text(hijo.nombre, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.parent),
                        contentDescription = "Encargado",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.hijo),
                        contentDescription = "Niño",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }

        // Sección de actividades
        Text("Actividades del día", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        if (actividades.isEmpty()) {
            Text("No hay actividades programadas", fontSize = 13.sp, color = Color.Gray)
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(actividades.take(5)) { actividad ->
                    ActivityCard(
                        title = actividad.titulo,
                        time = actividad.hora ?: "Sin hora",
                        imageUrl = actividad.imagenUrl
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Último reporte
        Text(
            text = "Último reporte: ${ultimoReporte?.fecha ?: "Sin reporte disponible"}",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(12.dp))

        if (ultimoReporte != null) {
            ReportCard(reporte = ultimoReporte)
        } else {
            Text("No se ha registrado un reporte reciente", fontSize = 13.sp, color = Color.Gray)
        }
    }
}

@Preview
@Composable
fun PreviewHomeParentScreen() {
    val alumno = Alumno(nombre = "Juanito Diaz")
    BAPPTheme {
        ParentHomeContent(hijo = alumno)
    }
}
