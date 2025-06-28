package com.falconteam.bapp.ui.main.aprobaradmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.theme.BAPPTheme

data class Admin(val name: String, val avatarResId: Int)

@Composable
fun AprobarAdminScreen() {
    val admins = remember {
        listOf(
            Admin("Carlos Mendoza Ruiz", R.drawable.parent),
            Admin("Lucía Gómez Fernández", R.drawable.parent),
            Admin("Daniela Ruiz Pérez", R.drawable.parent),
            Admin("Carlos Mendoza Ruiz", R.drawable.parent),
            Admin("Lucía Gómez Fernández", R.drawable.parent),
            Admin("Daniela Ruiz Pérez", R.drawable.parent),
        )
    }

    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .width(360.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xC4FFFFFF))
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aprobar docentes",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xCCFFFFFF)) // #FFFFFFCC
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                contentAlignment = Alignment.CenterEnd // lupa a la derecha
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar...") },
                    trailingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Contenedor redondeado para la lista
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                LazyColumn {
                    items(admins) { admin ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = admin.avatarResId),
                                contentDescription = "Avatar",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .padding(end = 8.dp)
                            )
                            Text(
                                text = admin.name,
                                modifier = Modifier.weight(1f),
                                fontSize = 16.sp
                            )
                            IconButton(onClick = { /* aprobar */ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_check),
                                    contentDescription = "Aprobar"
                                )
                            }
                            IconButton(onClick = { /* rechazar */ }) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_clear),
                                    contentDescription = "Rechazar"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AprobarAdminScreenPreview() {
    BAPPTheme {
        AprobarAdminScreen()
    }
}
