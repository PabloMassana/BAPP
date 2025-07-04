package com.falconteam.bapp.ui.main.homeadmin

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.falconteam.bapp.domain.models.CourseModel
import com.falconteam.bapp.domain.models.StudentModel
import com.falconteam.bapp.domain.models.toDomainModel
import com.falconteam.bapp.domain.usecases.admin.InsertarAlumnoCursoUseCase
import com.falconteam.bapp.domain.usecases.admin.InsertarCursoUseCase
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoCursosUseCase
import com.falconteam.bapp.ui.components.dropdown.DropdownTextField
import com.falconteam.bapp.ui.theme.BAPPTheme
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAdminBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState
) {
    var expandedDropdownBox by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    val insertarCursoUseCase = koinInject<InsertarCursoUseCase>()
    val insertarAlumnoCursoUseCase = koinInject<InsertarAlumnoCursoUseCase>()
    val obtenerListadoCursosUseCase = koinInject<ObtenerListadoCursosUseCase>()
    val coursesList = remember { mutableStateListOf<CourseModel>() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // Fetch the list of courses when the bottom sheet is shown
        obtenerListadoCursosUseCase().onSuccess { courses ->
            // Handle the list of courses if needed
            coursesList.clear()
            coursesList.addAll(courses.map { it.toDomainModel() })
        }.onFailure {
            Toast.makeText(context, "Error al cargar los cursos", Toast.LENGTH_SHORT).show()
        }
    }

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(text = "Agregar", style = MaterialTheme.typography.headlineMedium)

            Spacer(Modifier.height(24.dp))

            DropdownTextField(
                modifier = Modifier.fillMaxWidth(),
                expandedDropdownBox = expandedDropdownBox,
                onExpandableChange = { expandedDropdownBox = it },
                selectedOption = selectedOption,
                label = "¿Qué deseas agregar?",
                options = listOf("Alumno", "Grupo"),
                onDismissRequest = { expandedDropdownBox = false },
                onClickOption = { selectedOption = it }
            )

            when (selectedOption) {
                "Alumno" -> {
                    var studentName by remember { mutableStateOf("") }
                    var studentLastName by remember { mutableStateOf("") }
                    var selectedCourse by remember { mutableStateOf("") }
                    var expandedCourses by remember { mutableStateOf(false) }
                    var isLoading by remember { mutableStateOf(false) }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = studentName,
                        onValueChange = { studentName = it },
                        label = { Text("Nombre") },
                        placeholder = { Text("Ingrese el nombre del alumno") }
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = studentLastName,
                        onValueChange = { studentLastName = it },
                        label = { Text("Apellido") },
                        placeholder = { Text("Ingrese el apellido del alumno") }
                    )

                    DropdownTextField(
                        modifier = Modifier.fillMaxWidth(),
                        expandedDropdownBox = expandedCourses,
                        onExpandableChange = { expandedCourses = it },
                        selectedOption = selectedCourse,
                        label = "¿A que curso pertenece?",
                        options = coursesList.map { "${it.id} - ${it.level} - ${it.courseSection}" },
                        onDismissRequest = { expandedCourses = false },
                        onClickOption = { selectedCourse = it }
                    )

                    Spacer(Modifier.height(8.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        onClick = {
                            coroutineScope.launch {
                                val courseId = selectedCourse.split(" - ")[0].toIntOrNull()
                                if (courseId != null) {
                                    insertarAlumnoCursoUseCase(
                                        student = StudentModel(
                                            id = 0, // ID will be generated by the backend
                                            name = studentName,
                                            lastName = studentLastName,
                                            birthDate = "" // Birth date can be added later if needed
                                        ),
                                        idCourse = courseId
                                    ).onSuccess {
                                        Toast.makeText(context, "Alumno agregado exitosamente", Toast.LENGTH_SHORT).show()
                                        studentName = ""
                                        studentLastName = ""
                                        selectedCourse = ""
                                    }.onFailure {
                                        Toast.makeText(context, "Ocurrio un problema, intenta nuevamente", Toast.LENGTH_SHORT).show()
                                    }
                                } else {
                                    Toast.makeText(context, "Selecciona un curso válido", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA07A))
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                        } else {
                            Text(text = "Agregar Alumno", color = Color.White)
                        }
                    }
                }

                "Grupo" -> {
                    var levelGroup by remember { mutableStateOf("") }
                    var courseSection by remember { mutableStateOf("") }
                    var isLoading by remember { mutableStateOf(false) }

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = levelGroup,
                        onValueChange = { levelGroup = it },
                        label = { Text("Nivel") },
                        placeholder = { Text("Ingrese el nivel del grupo") }
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = courseSection,
                        onValueChange = { courseSection = it },
                        label = { Text("Seccion") },
                        placeholder = { Text("Ingresa la sección del grupo") }
                    )

                    Spacer(Modifier.height(8.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth().height(48.dp),
                        onClick = {
                            coroutineScope.launch {
                                insertarCursoUseCase(
                                    courseModel = CourseModel(
                                        id = 0, // ID will be generated by the backend
                                        level = levelGroup,
                                        idTeacher = 0, // This can be set later or fetched from a selection
                                        courseSection = courseSection,
                                    )
                                ).onSuccess {
                                    Toast.makeText(context, "Curso agregado exitosamente", Toast.LENGTH_SHORT).show()
                                }.onFailure {
                                    // Handle failure, e.g., show an error message
                                    Toast.makeText(context, "Ocurrio un problema, intenta nuevamente", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA07A))
                    ) {
                        if (isLoading) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                        } else {
                            Text(text = "Agregar Curso", color = Color.White)
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HomeAdminBottomSheetPreview() {
    BAPPTheme {
        HomeAdminBottomSheet(
            onDismissRequest = {},
            sheetState = rememberModalBottomSheetState()
        )
    }
}