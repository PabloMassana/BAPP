package com.falconteam.bapp.ui.main.notifications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.bapp.ui.theme.BAPPTheme
import androidx.compose.runtime.getValue
import com.falconteam.bapp.data.models.Notificacion

@Composable
fun NotificationsScreen(viewModel: NotificacionViewModel, usuarioId: String) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.cargarNotificaciones(usuarioId)
    }

    if (uiState.isLoading) {
        CircularProgressIndicator()
    } else {
        LazyColumn {
            /*items(uiState.notificaciones) { noti ->
                Card (
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { viewModel.marcarComoLeida(noti.id) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(noti.titulo, style = MaterialTheme.typography.titleMedium)
                        Text(noti.cuerpo, style = MaterialTheme.typography.bodyMedium)
                        Text("Fecha: ${noti.fecha}", style = MaterialTheme.typography.bodySmall)
                        if (!noti.leida) {
                            Text("Nueva", color = Color.Red)
                        }
                    }
                }
            }*/
        }
    }
}


@Preview
@Composable
private fun NotificactionsScreenPreview() {
    BAPPTheme {

    }
}