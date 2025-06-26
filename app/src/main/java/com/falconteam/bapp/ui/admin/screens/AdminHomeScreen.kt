    package com.falconteam.bapp.ui.admin.screens


    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Add
    import androidx.compose.material.icons.filled.Delete
    import androidx.compose.material.icons.filled.Person
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material3.*
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Brush
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.lifecycle.viewmodel.compose.viewModel
    import com.falconteam.bapp.ui.admin.viewmodel.AdminHomeViewModel
    import com.falconteam.bapp.ui.theme.BAPPTheme

    @Composable
    fun AdminHomeScreen(
        viewModel: AdminHomeViewModel = viewModel(),
        onNavigateToDelete: () -> Unit,
        onNavigateToApproveTeacher: () -> Unit
    ) {
        val grupos by viewModel.grupos.collectAsState()
        val actividades by viewModel.actividades.collectAsState()

        val paleCard = Color(0xFFFFD6C9)
        val mainBackground = Brush.verticalGradient(listOf(Color(0xFFFFA48B), Color.White))
        val buttonColor = Color(0xFFEB786F)

        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White
                ) {
                    NavigationBarItem(selected = true, onClick = { }, icon = {
                        Icon(Icons.Default.Add, contentDescription = "Agregar")
                    }, label = { Text("Agregar") })
                    NavigationBarItem(
                        selected = false,
                        onClick = onNavigateToDelete,
                        icon = { Icon(Icons.Default.Delete, contentDescription = "Borrar") },
                        label = { Text("Borrar") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = onNavigateToApproveTeacher, // ✅ Use callback
                        icon = { Icon(Icons.Default.Person, contentDescription = "Docentes") },
                        label = { Text("Docentes") }
                    )
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { }, containerColor = buttonColor) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
            containerColor = Color.Transparent
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(mainBackground)
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(paleCard, RoundedCornerShape(24.dp))
                        .padding(24.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                        Text("Bienvenido", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Text("Administración", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(12.dp))
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color.LightGray)
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = Color.White, modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

                Spacer(Modifier.height(24.dp))

                // Grupos existentes
                Text("Grupos existentes", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Spacer(Modifier.height(8.dp))
                grupos.forEach {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(paleCard, RoundedCornerShape(12.dp))
                            .padding(12.dp)
                            .padding(bottom = 8.dp)
                    ) {
                        Column {
                            Text(it.nombre, fontWeight = FontWeight.Medium)
                            Spacer(Modifier.height(8.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                listOf("Alumnos", "Padres", "Docente").forEach { label ->
                                    Button(
                                        onClick = {},
                                        shape = RoundedCornerShape(50),
                                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
                                    ) {
                                        Text(label, fontSize = 12.sp, color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                }

                // Alumnos registrados
                Spacer(Modifier.height(16.dp))
                Text("Alumnos registrados", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Spacer(Modifier.height(8.dp))
                grupos.take(3).forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(paleCard, RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(it.nombre)
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text("Asignar encargado", fontSize = 12.sp, color = Color.White)
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                }

                // Padres registrados
                Spacer(Modifier.height(16.dp))
                Text("Padres registrados", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                Spacer(Modifier.height(8.dp))
                grupos.take(3).forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(paleCard, RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(it.nombre)
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
                        ) {
                            Text("Asignar hijo", fontSize = 12.sp, color = Color.White)
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun AdminHomeScreenPreview() {
        BAPPTheme {
            AdminHomeScreen(
                onNavigateToDelete = {},
                onNavigateToApproveTeacher = {}
            )
        }
    }