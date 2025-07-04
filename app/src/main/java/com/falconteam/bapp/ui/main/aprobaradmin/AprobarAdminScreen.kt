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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.theme.BAPPTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AprobarAdminScreen(
    viewModel: AprobarAdminViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onStart()
    }

    AprobarAdminScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp),
        uiState = uiState,
        onSearchQueryChange = viewModel::updateSearchQuery,
        onApproveTeacher = { userId, parentId ->
            viewModel.approveTeacher(userId, parentId)
        }
    )
}

@Composable
fun AprobarAdminScreenContent(
    modifier: Modifier = Modifier,
    uiState: AprobarAdminUiState,
    onSearchQueryChange: (String) -> Unit = {},
    onApproveTeacher: (String, Int) -> Unit = { _, _ ->}
) {
    Column(
        modifier = modifier,
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
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                value = uiState.searchQuery,
                onValueChange = onSearchQueryChange,
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
                items(uiState.parentsList) { parent ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.parent),
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = "${parent.name} ${parent.lastname}",
                            modifier = Modifier.weight(1f),
                            fontSize = 16.sp
                        )

                        IconButton(onClick = {
                            onApproveTeacher(parent.userId, parent.id)
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = "Aprobar"
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
fun AprobarAdminScreenPreview() {
    BAPPTheme {
        AprobarAdminScreenContent(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFA07A), Color.White)
                    )
                )
                .padding(16.dp),
            uiState = AprobarAdminUiState(),
        )
    }
}
