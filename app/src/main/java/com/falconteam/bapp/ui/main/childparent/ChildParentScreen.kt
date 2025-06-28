package com.falconteam.bapp.ui.main.childparent

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown
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
import com.falconteam.bapp.data.components.TaskCard
import com.falconteam.bapp.data.models.Alumno
import com.falconteam.bapp.data.models.DetalleReporte
import com.falconteam.bapp.data.models.Reporte
import com.falconteam.bapp.data.models.Task
import com.falconteam.bapp.ui.main.homeparent.EmojiLine
import com.falconteam.bapp.ui.main.homeparent.ReportRatingLine
import com.falconteam.bapp.ui.theme.BAPPTheme

@Composable
fun ChildParentScreen(
    alumno: Alumno,
    reportes: List<Reporte>,
    tareasPendientes: List<Task>,
    tareasCompletadas: List<Task>,
    onTaskCheckedChange: (Task, Boolean) -> Unit = { _, _ -> }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        // Header con datos del alumno
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFD3BD)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hijo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(alumno.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    alumno.edad()?.let {
                        Text("Edad: $it años", fontSize = 14.sp)
                    } ?: Text("Edad: N/D", fontSize = 14.sp)

                    Text("Nacimiento: ${alumno.fechaNacimiento ?: "N/D"}", fontSize = 14.sp)
                    Text("Nivel: Kinder    Grupo: ${alumno.idCurso}", fontSize = 14.sp)
                }
            }
        }

        // Reportes desplegables
        Text("Últimos reportes", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        if (reportes.isEmpty()) {
            Text("No hay reportes recientes", fontSize = 13.sp, color = Color.Gray)
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                reportes.forEach { reporte ->
                    ExpandableReportCard(reporte)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tareas pendientes
        Text("Tareas para ti", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        tareasPendientes.forEach { task ->
            TaskCard(task = task, onCheckedChange = { isChecked ->
                onTaskCheckedChange(task, isChecked)
            })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tareas completadas
        Text("Tareas completadas", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        tareasCompletadas.forEach { task ->
            TaskCard(task = task, enabled = false, checked = true)
        }
    }
}

@Composable
fun ExpandableReportCard(reporte: Reporte) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Reporte del ${reporte.fecha}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expanded) "Colapsar" else "Expandir"
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                reporte.detalles.forEach { detalle ->
                    ReportRatingLine(titulo = detalle.titulo, estrellas = detalle.estrellas)
                }

                if (!reporte.comentarioDocente.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    EmojiLine(
                        emoji = "💡",
                        title = "Comentario del docente",
                        content = reporte.comentarioDocente
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChildParentScreenPreview() {
    val dummyAlumno = Alumno(
        id = "alumno1",
        nombre = "Juanito Alejandro",
        idCurso = "A",
        idPadre = "padre123",
        fechaNacimiento = "2020-01-01"
    )

    val reportes = listOf(
        Reporte(
            fecha = "2025-06-24",
            detalles = listOf(
                DetalleReporte("Estado de animo", 4),
                DetalleReporte("Interacciones sociales", 3),
                DetalleReporte("Participacion en clase", 5),
                DetalleReporte("Concentración", 4),
                DetalleReporte("Higiene", 3)
            ),
            comentarioDocente = "Buen desempeño hoy, participó activamente."
        )
    )

    val tareas = listOf(
        Task("1", "Practica los colores con Juanito", "Refuerzo de colores primarios"),
        Task("2", "Practica los números del 1 al 10", "Aprender a contar")
    )

    val completadas = listOf(
        Task("3", "Colorea un dibujo", "Motricidad fina", completada = true)
    )

    BAPPTheme {
        ChildParentScreen(
            alumno = dummyAlumno,
            reportes = reportes,
            tareasPendientes = tareas,
            tareasCompletadas = completadas
        )
    }
}
