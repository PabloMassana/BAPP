package com.falconteam.bapp.ui.main.homeparent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.data.models.Reporte
import com.falconteam.bapp.ui.theme.BAPPTheme
import com.falconteam.bapp.R

@Composable
fun HomeParentScreen(
    actividades: List<Actividad> = emptyList(),
    ultimoReporte: Reporte? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0D2)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("¡Bienvenido!, encargado de", fontSize = 14.sp)
                Text("Juanito Diaz", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(end = 4.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Actividades del día
        Text("Actividades del día", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        if (actividades.isEmpty()) {
            Text("No hay actividades programadas", fontSize = 13.sp, color = Color.Gray)
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                actividades.forEach {
                    ActivityCard(it.titulo, it.hora, it.iconoRes)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Último reporte
        Text("Último reporte: ${ultimoReporte?.fecha ?: "Sin reporte disponible"}", fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(12.dp))

        if (ultimoReporte != null) {
            ReportCard(ultimoReporte)
        } else {
            Text("No se ha registrado un reporte reciente", fontSize = 13.sp, color = Color.Gray)
        }
    }
}


@Preview
@Composable
private fun HomeScreenPreview() {
    BAPPTheme {
        HomeParentScreen()
    }
}