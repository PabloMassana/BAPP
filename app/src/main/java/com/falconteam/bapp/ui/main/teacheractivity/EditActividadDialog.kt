package com.falconteam.bapp.ui.main.teacheractivity

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.falconteam.bapp.data.models.Actividad
import com.falconteam.bapp.domain.models.ActividadModel
import com.falconteam.bapp.domain.usecases.teacher.UpsertActividadUseCase
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EditActividadDialog(
    actividad: Actividad,
    onDismiss: () -> Unit
) {
    val upsertActividadUseCase = koinInject<UpsertActividadUseCase>()
    val scope = rememberCoroutineScope()

    var titulo by remember { mutableStateOf(actividad.titulo) }
    var fecha by remember { mutableStateOf(SimpleDateFormat("yyyy-MM-dd").format(actividad.fecha)) }
    var hora by remember { mutableStateOf(SimpleDateFormat("HH:mm", Locale.getDefault()).format(actividad.hora)) }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            val newDate = "%04d-%02d-%02d".format(year, month + 1, dayOfMonth)
            fecha = newDate
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Actividad") },
        text = {
            Column {
                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { datePickerDialog.show() }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Seleccionar Fecha")
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = hora,
                    onValueChange = { hora = it },
                    label = { Text("Hora (HH:mm)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val parsedFecha = try {
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(fecha)
                } catch (_: Exception) {
                    actividad.fecha
                }

                val parsedHora = try {
                    val date = SimpleDateFormat("HH:mm", Locale.getDefault()).parse(hora)
                    Time(date?.time ?: actividad.hora.time)
                } catch (_: Exception) {
                    actividad.hora
                }

                val actividadModel = ActividadModel(
                    id = actividad.id,
                    titulo = titulo,
                    fecha = parsedFecha ?: actividad.fecha,
                    hora = parsedHora,
                    imagenUrl = actividad.imagenUrl,
                    cursoId = actividad.cursoId
                )

                scope.launch {
                    upsertActividadUseCase(actividadModel)
                    onDismiss()
                }
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
