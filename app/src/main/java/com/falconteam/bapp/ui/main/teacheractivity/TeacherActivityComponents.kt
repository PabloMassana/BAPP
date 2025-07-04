package com.falconteam.bapp.ui.main.teacheractivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.domain.models.ActividadModel
import com.falconteam.bapp.ui.utils.obtenerDiaDesdeFecha
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HeaderAndSearchField() {
    Text("Actividades de la semana:", fontWeight = FontWeight.Bold, fontSize = 15.sp)
    Spacer(modifier = Modifier.height(6.dp))
    SearchField(placeholder = "Buscar por semana...")
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

@Composable
fun ActivitiesList(
    actividades: List<ActividadModel>,
    onEdit: (Actividad) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(actividades.groupBy { it.fecha }.toList()) { (fecha, actividades) ->
            val dia = obtenerDiaDesdeFecha(fecha)
            DayTeacherCard(
                day = dia,
                date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(fecha),
                activities = actividades,
                onEdit = { onEdit(it) }
            )
        }
    }
}

@Composable
fun DayTeacherCard(
    day: String,
    date: String,
    activities: List<ActividadModel>,
    onEdit: (Actividad) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(day, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(date, fontSize = 13.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(12.dp))

        activities.forEach { activity ->
            ActivityTeacherItem(activity = activity, onEdit = { /*onEdit(activity)*/ })
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun ActivityTeacherItem(activity: ActividadModel, onEdit: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(1.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxSize()
        ) {
            Row(modifier = Modifier
                .fillMaxSize()
                .padding(end = 36.dp)) {

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(80.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp))
                        .background(Color.LightGray)
                )

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                        .weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(activity.titulo, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    Text(activity.hora.toString(), fontSize = 12.sp, color = Color.Gray)
                    Text("Imágenes disponibles", fontSize = 10.sp, color = Color(0xFFE07055))
                }
            }
        }

        IconButton(
            onClick = onEdit,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp)
                .size(28.dp)
                .background(Color(0xFFF8F8F8), shape = CircleShape)
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