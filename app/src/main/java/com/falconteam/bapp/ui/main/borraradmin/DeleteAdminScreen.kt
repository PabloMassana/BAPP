package com.falconteam.bapp.ui.main.borraradmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.theme.BAPPTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DeleteAdminScreen(
    viewModel: DeleteAdminViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DeleteAdminScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp),
        uiState = uiState,
        onDropdownExpanded = viewModel::onDropdownExpanded

    )
}

@Composable
fun DeleteAdminScreenContent(
    modifier: Modifier = Modifier,
    uiState: DeleteAdminUiState,
    onDropdownExpanded: (Boolean) -> Unit = {},
) {
    Column(modifier = modifier) {
        // Selector de curso
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .clickable {
                    onDropdownExpanded(true)
                }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Seleccionar curso", color = Color.Gray, style = TextStyle(
                        fontSize = 14.sp, fontWeight = FontWeight.Normal
                    )
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Expandir"
                )
            }
        }

        DropdownMenu(
            expanded = uiState.expandedDropdown,
            onDismissRequest = {
                onDropdownExpanded(false)
            }
        ) {
            uiState.courses.forEach { course ->
                DropdownMenuItem(
                    text = { Text(text = "${course.level} - ${course.courseSection}") },
                    onClick = {
                        // TODO: handle on dropdown selection
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp)) // Espacio entre filtros

        // Campo de búsqueda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                BasicTextField(
                    value = uiState.searchQuery,
                    onValueChange = {
                        // TODO: handle search text change
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 12.dp),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (uiState.searchQuery.isEmpty()) {
                            Text("Buscar...", color = Color.Gray)
                        }
                        innerTextField()
                    }
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "Buscar"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de estudiantes
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center),
                    color = Color(0xFFFFA07A)
                )
                return@Box
            }
            
            if (uiState.selectedCourse == null) {
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "Por favor, selecciona un curso para ver los estudiantes.",
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray, textAlign = TextAlign.Center)
                )
                return@Box
            }
            
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(uiState.studentsCourseSelected) { student ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = student.id),
                            contentDescription = student.name,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "${student.name} ${student.lastName}",
                            modifier = Modifier.weight(1f)
                        )

                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_trash),
                                contentDescription = "Eliminar"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteAdminScreenPreview() {
    BAPPTheme {
        DeleteAdminScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFA07A), Color.White)
                    )
                )
                .padding(16.dp),
            uiState = DeleteAdminUiState()
        )
    }
}
