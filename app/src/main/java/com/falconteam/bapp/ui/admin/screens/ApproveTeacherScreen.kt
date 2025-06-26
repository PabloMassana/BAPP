package com.falconteam.bapp.ui.admin.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.falconteam.bapp.ui.admin.viewmodel.ApproveTeachersViewModel
import com.falconteam.bapp.ui.admin.state.ApproveTeachersState
import com.falconteam.bapp.R

@Composable
fun ApproveTeacherScreen(
    viewModel: ApproveTeachersViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToDelete: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val gradient = Brush.verticalGradient(listOf(Color(0xFFFFA48B), Color.White))
    val cardColor = Color(0xFFFFD6C9)

    val filteredTeachers = remember(state.teachers, state.searchQuery) {
        state.teachers.filter {
            it.name.contains(state.searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateBack,
                    icon = { Icon(Icons.Default.Add, contentDescription = "Agregar") },
                    label = { Text("Agregar") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateToDelete,
                    icon = { Icon(Icons.Default.Delete, contentDescription = "Borrar") },
                    label = { Text("Borrar") }
                )

                NavigationBarItem(selected = true, onClick = { }, icon = {
                    Icon(Icons.Default.Person, contentDescription = "Docentes")
                }, label = { Text("Docentes") })
            }
        },
        containerColor = Color.Transparent
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                "Aprobar docentes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(cardColor, RoundedCornerShape(16.dp))
                    .padding(16.dp)
            )

            Spacer(Modifier.height(16.dp))

            TextField(
                value = state.searchQuery,
                onValueChange = { viewModel.updateSearch(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(50)),
                placeholder = { Text("Buscar...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f) // Allows LazyColumn to scroll
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredTeachers) { teacher ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = teacher.imageRes),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    text = teacher.name,
                                    fontSize = 14.sp,
                                    maxLines = 1,
                                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                                )
                            }

                            Row {
                                IconButton(onClick = { viewModel.approveTeacher(teacher.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Aprobar",
                                        tint = Color(0xFF4CAF50)
                                    )
                                }
                                IconButton(onClick = { viewModel.rejectTeacher(teacher.id) }) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Rechazar",
                                        tint = Color(0xFFF44336)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
