package com.falconteam.bapp.ui.main.actividadteacher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.theme.BAPPTheme

@Composable
fun ActividadTeacherScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFF8A65), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text("Actividades de la semana:", fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(6.dp))
            SearchField(placeholder = "Buscar por semana...")

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(dummyDaysWithActivities) { (day, date, activities) ->
                    DayTeacherCard(day = day, date = date, activities = activities)
                }
            }
        }

        FloatingActionButton(
            onClick = { /* Acción de agregar */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFFFF8A65),
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar actividad")
        }
    }
}

@Composable
fun DayTeacherCard(day: String, date: String, activities: List<DummyActivity>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(day, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(date, fontSize = 13.sp, color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(12.dp))

            activities.forEach { activity ->
                ActivityTeacherItem(activity)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ActivityTeacherItem(activity: DummyActivity) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 36.dp)
            ) {
                Image(
                    painter = painterResource(id = activity.imageRes),
                    contentDescription = activity.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(80.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp))
                )

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(activity.title, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    Text(activity.hour, fontSize = 12.sp, color = Color.Gray)
                    Text(
                        "5 imágenes disponibles",
                        fontSize = 10.sp,
                        color = Color(0xFFE07055)
                    )
                }
            }
        }

        IconButton(
            onClick = { /* Acción de editar */ },
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .size(28.dp)
                .background(
                    color = Color(0xFFF8F8F8),
                    shape = CircleShape
                )
        ) {
            Icon(
                Icons.Default.Edit,
                contentDescription = "Editar",
                tint = Color(0xFFE07055),
                modifier = Modifier.size(16.dp)
            )
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

// Datos ficticios de ejemplo
val dummyDaysWithActivities = listOf(
    Triple(
        "Lunes", "17-02-2025", listOf(
            DummyActivity("Actividades con plastilina", "8:00 am", R.drawable.parent),
            DummyActivity("Paseo del trabajo", "9:00 am", R.drawable.hijo)
        )
    ),
    Triple(
        "Martes", "18-02-2025", listOf(
            DummyActivity("Pintura libre", "9:00 am", R.drawable.hijo)
        )
    ),
    Triple(
        "Miércoles", "19-02-2025", listOf(
            DummyActivity("Actividades con plastilina", "8:00 am", R.drawable.parent),
            DummyActivity("Paseo del trabajo", "9:00 am", R.drawable.hijo)
        )
    )
)

data class DummyActivity(
    val title: String,
    val hour: String,
    val imageRes: Int
)

@Preview(showBackground = true)
@Composable
fun ActividadTeacherScreenPreview() {
    BAPPTheme {
        ActividadTeacherScreen()
    }
}
