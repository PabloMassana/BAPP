package com.falconteam.bapp.ui.main.homeparent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R
import com.falconteam.bapp.data.models.Reporte
import com.falconteam.bapp.utils.ReporteUtils

@Composable
fun ReportCard(reporte: Reporte) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            reporte.detalles.forEach { detalle ->
                ReportRatingLine(detalle.titulo, detalle.estrellas)
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

@Composable
fun ReportRatingLine(titulo: String, estrellas: Int) {
    val iconRes = when (titulo.lowercase()) {
        "estado de animo" -> R.drawable.ic_smileface
        "interacciones sociales" -> R.drawable.ic_handshake
        "participacion en clase" -> R.drawable.ic_happyperson
        else -> R.drawable.ic_star_filled
    }

    val descripcion = when (titulo.lowercase()) {
        "estado de animo" -> ReporteUtils.obtenerMensaje(estrellas, ReporteUtils.mensajesEstadoAnimo)
        "interacciones sociales" -> ReporteUtils.obtenerMensaje(estrellas, ReporteUtils.mensajesInteracciones)
        "participacion en clase" -> ReporteUtils.obtenerMensaje(estrellas, ReporteUtils.mensajesParticipacion)
        else -> "Sin descripción disponible."
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = titulo,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(titulo, fontWeight = FontWeight.SemiBold)
            Row {
                repeat(5) { i ->
                    Icon(
                        painter = painterResource(
                            id = if (i < estrellas) R.drawable.ic_star_filled else R.drawable.ic_star_outline
                        ),
                        contentDescription = "Estrella ${i + 1}",
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(descripcion, fontSize = 13.sp, color = Color.Gray)
        }
    }
}

@Composable
fun EmojiLine(emoji: String, title: String, content: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(emoji, fontSize = 20.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("$title:", fontWeight = FontWeight.SemiBold)
            Text(content, fontSize = 13.sp)
        }
    }
}
