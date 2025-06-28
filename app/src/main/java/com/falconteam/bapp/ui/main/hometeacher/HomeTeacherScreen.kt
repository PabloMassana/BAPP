package com.falconteam.bapp.ui.main.hometeacher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R

@Composable
fun HomeTeacherScreen() {
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

            // Header del docente
            TeacherHeader(
                name = "Brandon Cornejo",
                avatarRes = R.drawable.parent
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Actividades
            Text(
                text = "Últimas actividades registradas",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            val actividades = listOf(
                Triple("Dibujar y colorear", "8:00 am", R.drawable.logo_background),
                Triple("Manualidades con plastilina", "10:00 am", R.drawable.logo_background)
            )

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(actividades) { (titulo, hora, img) ->
                    ActivityCard(title = titulo, time = hora, imageRes = img)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Grupos
            Text(
                text = "Tus grupos",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            val grupos = listOf("Kínder 4 - Sección A", "Kínder 4 - Sección B")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                grupos.forEach {
                    GroupCard(
                        groupName = it,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Botón flotante
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

@Composable
fun TeacherHeader(name: String, avatarRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0x99FFFFFF) // Translúcido
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = CircleShape,
                color = Color.LightGray,
                modifier = Modifier.size(64.dp)
            ) {
                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = "Avatar",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Bienvenido, docente:",
                fontSize = 13.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ActivityCard(title: String, time: String, imageRes: Int) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(220.dp)
            .height(90.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Imagen a la izquierda
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp))
            )

            // Texto a la derecha
            Column(
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 8.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun GroupCard(groupName: String, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFE0D0)
        ),
        modifier = modifier
            .height(170.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = groupName,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            GradientButton("Ver Estudiantes")
            GradientButton("Ver encargados")
        }
    }
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFFFF8A65), Color(0xFFFF7043))
                ),
                shape = RoundedCornerShape(50)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTeacherScreenPreview() {
    HomeTeacherScreen()
}
