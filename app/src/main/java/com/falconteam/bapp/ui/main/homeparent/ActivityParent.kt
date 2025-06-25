package com.falconteam.bapp.ui.main.homeparent

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.falconteam.bapp.data.models.Actividad

@Composable
fun ActivityParent(actividades: List<Actividad>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFBF9))
            .padding(16.dp)
    ) {
        Text(
            text = "Actividades del día",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (actividades.isEmpty()) {
            Text(
                text = "No hay actividades registradas.",
                color = Color.Gray,
                fontSize = 14.sp
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(actividades.size) { index ->
                    ActividadCard(actividad = actividades[index])
                }
            }
        }
    }
}

@Composable
fun ActividadCard(actividad: Actividad) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFCEFEA)),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = actividad.titulo, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Fecha: ${actividad.fecha}", fontSize = 14.sp, color = Color.Gray)
            Text(text = "Hora: ${actividad.hora}", fontSize = 14.sp, color = Color.Gray)

            if (actividad.Imagenes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    actividad.Imagenes.take(3).forEach { url ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.size(80.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(url),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActividadMiniCard(actividad: Actividad) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(150.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFCEFEA)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = actividad.titulo,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = actividad.fecha,
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = actividad.hora,
                fontSize = 12.sp,
                color = Color.Gray
            )

            if (actividad.Imagenes.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Image(
                    painter = rememberAsyncImagePainter(actividad.Imagenes.first()),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
    }
}
