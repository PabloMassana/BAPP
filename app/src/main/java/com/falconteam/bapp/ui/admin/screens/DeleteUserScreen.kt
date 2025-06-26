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
import com.falconteam.bapp.ui.admin.viewmodel.DeleteUserViewModel
import com.falconteam.bapp.ui.admin.viewmodel.User
import org.koin.compose.viewmodel.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun DeleteUserScreen(
    viewModel: DeleteUserViewModel = koinViewModel(),
    onNavigateBack: () -> Unit
) {
    val background = Brush.verticalGradient(listOf(Color(0xFFFFA48B), Color.White))
    val paleCard = Color(0xFFFFD6C9)
    val orange = Color(0xFFEB786F)

    var selectedGroup by remember { mutableStateOf(viewModel.groups.first()) }
    var searchText by remember { mutableStateOf("") }

    val filteredUsers = remember(searchText) {
        viewModel.users.filter {
            it.name.contains(searchText, ignoreCase = true)
        }
    }

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = false,
                    onClick = onNavigateBack, // Navigate back to AdminHomeScreen
                    icon = { Icon(Icons.Default.Add, contentDescription = "Agregar") },
                    label = { Text("Agregar") }
                )

                NavigationBarItem(selected = true, onClick = { }, icon = {
                    Icon(Icons.Default.Delete, contentDescription = "Borrar")
                }, label = { Text("Borrar") })

                NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                    Icon(Icons.Default.Person, contentDescription = "Docentes")
                }, label = { Text("Docentes") })
            }
        },
        containerColor = Color.Transparent
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(background)
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.height(24.dp))

            // Dropdown
            ExposedDropdownMenuBox(
                expanded = false,
                onExpandedChange = {},
            ) {
                TextField(
                    value = selectedGroup,
                    onValueChange = {},
                    readOnly = true,
                    label = null,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = false) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(50))
                        .background(paleCard),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }

            Spacer(Modifier.height(12.dp))

            // Search
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
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

            // User list
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredUsers) { user ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = user.avatarRes),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(Modifier.width(10.dp))
                                Text(user.name, fontSize = 14.sp)
                            }
                            IconButton(onClick = { viewModel.deleteUser(user) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
