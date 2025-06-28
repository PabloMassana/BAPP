package com.falconteam.bapp.ui.main.bitacoraparent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.falconteam.bapp.ui.theme.BAPPTheme

@Composable
fun BitacoraParentScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFF8A65), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        item {
            Text("Actividades de la semana:", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(6.dp))
            SearchField(placeholder = "Buscar por semana...")

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                item {
                    DayActivityCard(
                        day = "Lunes",
                        activities = listOf(
                            DummyActivity("Actividades con plastilina", "8:00 am", R.drawable.parent),
                            DummyActivity("Paseo del trabajo", "10:00 am", R.drawable.parent)
                        )
                    )
                }

                item {
                    DayActivityCard(
                        day = "Martes",
                        activities = listOf(
                            DummyActivity("Pintura libre", "9:00 am", R.drawable.hijo)
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Todos los reportes", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(6.dp))
            SearchField(placeholder = "Buscar día...")
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(dummyReports) { report ->
            ReportItem(fecha = report)
        }
    }
}

@Composable
fun SearchField(placeholder: String) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = { searchText = it },
        placeholder = { Text(placeholder, fontSize = 13.sp) },
        trailingIcon = {
            Icon(Icons.Default.DateRange, contentDescription = "Calendario")
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        )
    )
}

data class DummyActivity(
    val title: String,
    val hour: String,
    val imageRes: Int
)

@Composable
fun DayActivityCard(day: String, activities: List<DummyActivity>) {
    Card(
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier
            .width(190.dp)
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(day, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(8.dp))
            activities.forEach { activity ->
                ActivityItem(activity)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ActivityItem(activity: DummyActivity) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = activity.imageRes),
                contentDescription = activity.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            )

            Column(
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 8.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(activity.title, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                Text(activity.hour, fontSize = 12.sp, color = Color.Gray)
                Text("5 imágenes disponibles", fontSize = 10.sp, color = Color(0xFFE07055))
            }
        }
    }
}

@Composable
fun ReportItem(fecha: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(fecha, modifier = Modifier.weight(1f), fontSize = 14.sp)
        Image(
            painter = painterResource(id = R.drawable.ic_eye),
            contentDescription = "Ver reporte",
        )
    }
}

val dummyReports = listOf(
    "Lunes 24 de enero 2025",
    "Miércoles 01 de octubre de 2024",
    "Miércoles 24 de septiembre de 2024",
    "Viernes 30 de agosto de 2024"
)

@Preview(showBackground = true)
@Composable
fun BitacoraParentScreenPreview() {
    BAPPTheme {
        BitacoraParentScreen()
    }
}
