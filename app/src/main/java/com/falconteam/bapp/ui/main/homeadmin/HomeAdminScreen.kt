package com.falconteam.bapp.ui.main.homeadmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.theme.BAPPTheme

@Composable
fun HomeAdminScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            AdminHeader(name = "Administración", avatarRes = R.drawable.hijo)

            Spacer(modifier = Modifier.height(24.dp))

            // ===== Grupos existentes =====
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0D0))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Grupos existentes",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    val grupos = listOf("Kinder 4 - Sección A", "Kinder 4 - Sección A", "Kinder 4 - Sección A")

                    grupos.forEach { grupo ->
                        GroupRowCompact(grupo)
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    // Flecha a la derecha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Expand",
                            tint = Color.Black,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ===== Alumnos registrados =====
            Text(
                text = "Alumnos registrados",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            val alumnos = listOf("Kinder 4 - Sección A", "Kinder 4 - Sección A", "Kinder 4 - Sección A")

            alumnos.forEach {
                ActionRowCompact(it, actionText = "Asignar encargado")
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ===== Padres registrados =====
            Text(
                text = "Padres registrados",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            val padres = listOf("Kinder 4 - Sección A", "Kinder 4 - Sección A", "Kinder 4 - Sección A")

            padres.forEach {
                ActionRowCompact(it, actionText = "Asignar hijo")
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Flecha a la derecha
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Expand",
                    tint = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        // Botón flotante
        FloatingActionButton(
            onClick = { /* TODO */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
            shape = CircleShape,
            containerColor = Color(0xFFFF8A65)
        ) {
            Text("+", fontSize = 24.sp, color = Color.White)
        }
    }
}

@Composable
fun AdminHeader(name: String, avatarRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 32.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.7f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Bienvenido",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                shape = CircleShape,
                color = Color.LightGray,
                modifier = Modifier.size(60.dp)
            ) {
                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = "Avatar",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun GroupRowCompact(grupo: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = grupo,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.weight(1f)
            )
            PillButton("Alumnos", 64.dp)
            Spacer(modifier = Modifier.width(4.dp))
            PillButton("Padres", 58.dp)
            Spacer(modifier = Modifier.width(4.dp))
            PillButton("docente", 70.dp)
        }
    }
}

@Composable
fun ActionRowCompact(title: String, actionText: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
            PillButton(text = actionText, width = 118.dp)
        }
    }
}

@Composable
fun PillButton(text: String, width: Dp) {
    Box(
        modifier = Modifier
            .width(width)
            .height(30.dp)
            .clip(RoundedCornerShape(50))
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xFFFF8A65), Color(0xFFFF7043))
                )
            )
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeAdminScreenPreview() {
    BAPPTheme {
        HomeAdminScreen()
    }
}
