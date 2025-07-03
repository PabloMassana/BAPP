package com.falconteam.bapp.ui.main.teacheractivity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.ui.theme.BAPPTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TeacherActivitiesScreen(
    viewModel: TeacherActivitiesViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsState()
    val actividadSeleccionadaState = remember { mutableStateOf<Actividad?>(null) }
    val actividadSeleccionada = actividadSeleccionadaState.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFFF8A65), Color.White)))
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderAndSearchField()

            Spacer(modifier = Modifier.height(16.dp))

            if (state.value.loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                ActivitiesList(
                    actividades = state.value.actividades,
                    onEdit = { actividadSeleccionadaState.value = it }
                )
            }
        }

        if (actividadSeleccionada != null) {
            EditActividadDialog(
                actividad = actividadSeleccionada,
                onDismiss = {
                    actividadSeleccionadaState.value = null
                    viewModel.cargarActividades()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActividadTeacherScreenPreview() {
    BAPPTheme {
        TeacherActivitiesScreen()
    }
}
