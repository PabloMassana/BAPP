package com.falconteam.bapp.ui.teacher.screens



import android.app.DatePickerDialog
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.falconteam.bapp.ui.theme.BAPPTheme
import java.util.*

data class Actividad(
    val nombre: String,
    val fecha: String,
    val hora: String,
    val imagenUri: Uri? = null
)

@Composable
fun TeacherActivitiesScreen() {
    val PaleCardColor = Color(0xFFFFD6C9)
    val OrangeButtonColor = Color(0xFFFF7F56)
    val context = LocalContext.current

    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var showDialog by remember { mutableStateOf(false) }

    var actividadNombre by remember { mutableStateOf("") }
    var actividadFecha by remember { mutableStateOf("") }
    var actividadHora by remember { mutableStateOf("") }
    var imagenUri by remember { mutableStateOf<Uri?>(null) }

    val actividades = remember { mutableStateListOf<Actividad>() }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imagenUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFF7F56), Color(0xFFFFB8A9))
                )
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Actividades de la semana",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Buscar actividad") },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = {
                val calendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        actividadFecha = "$dayOfMonth/${month + 1}/$year"
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        val filas = actividades.chunked(5)
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            filas.forEach { fila ->
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(fila.size) { index ->
                        val actividad = fila[index]
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = PaleCardColor),
                            modifier = Modifier
                                .width(200.dp)
                                .height(180.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(text = actividad.nombre, color = Color.Black)
                                Text(text = actividad.fecha, color = Color.Black)
                                Text(text = actividad.hora, color = Color.Black)
                                actividad.imagenUri?.let {
                                    Image(
                                        painter = rememberAsyncImagePainter(it),
                                        contentDescription = "Imagen",
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp, end = 24.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = { showDialog = true },
            containerColor = OrangeButtonColor,
            shape = CircleShape
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar actividad", tint = Color.White)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (actividadNombre.isNotBlank()) {
                            actividades.add(
                                Actividad(
                                    nombre = actividadNombre,
                                    fecha = actividadFecha,
                                    hora = actividadHora,
                                    imagenUri = imagenUri
                                )
                            )
                            actividadNombre = ""
                            actividadFecha = ""
                            actividadHora = ""
                            imagenUri = null
                            showDialog = false
                        }
                    }
                ) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            },
            title = { Text("Nueva actividad") },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    OutlinedTextField(
                        value = actividadNombre,
                        onValueChange = { actividadNombre = it },
                        label = { Text("Nombre de la actividad") }
                    )
                    OutlinedTextField(
                        value = actividadFecha,
                        onValueChange = { actividadFecha = it },
                        label = { Text("Fecha (DD/MM/AAAA)") },
                        readOnly = true,
                        trailingIcon = {
                            IconButton(onClick = {
                                val calendar = Calendar.getInstance()
                                DatePickerDialog(
                                    context,
                                    { _, year, month, dayOfMonth ->
                                        actividadFecha = "$dayOfMonth/${month + 1}/$year"
                                    },
                                    calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)
                                ).show()
                            }) {
                                Icon(Icons.Default.DateRange, contentDescription = "Calendario")
                            }
                        }
                    )
                    OutlinedTextField(
                        value = actividadHora,
                        onValueChange = { actividadHora = it },
                        label = { Text("Hora") }
                    )
                    Button(onClick = {
                        imagePickerLauncher.launch("image/*")
                    }) {
                        Text("Seleccionar imagen")
                    }
                    imagenUri?.let {
                        Image(
                            painter = rememberAsyncImagePainter(it),
                            contentDescription = "Vista previa imagen",
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        )
    }
}



@Preview(showSystemUi = true)
@Composable
fun TeacherActivitiesScreenPreview() {
    BAPPTheme {
        TeacherActivitiesScreen()
    }
}
