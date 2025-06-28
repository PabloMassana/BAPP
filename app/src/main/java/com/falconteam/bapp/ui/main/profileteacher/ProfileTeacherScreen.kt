package com.falconteam.bapp.ui.main.profileteacher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.theme.BAPPTheme

@Composable
fun ProfileTeacherScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        ProfileHeader(
            name = "María González",
            email = "marigonz@school.edu",
            role = "Docente",
            avatarRes = R.drawable.parent // Reemplaza con tu imagen real si es necesario
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Grupos y estudiantes",
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        GroupSection(
            groupName = "Kinder - Grupo A",
            students = listOf(
                "Juanito Alejandro López" to R.drawable.hijo,
                "Sofía Martínez López" to R.drawable.hijo,
                "Mateo Ramírez Torres" to R.drawable.hijo
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        GroupSection(
            groupName = "Preparatoria - Grupo B",
            students = listOf(
                "Juanito Alejandro López" to R.drawable.hijo,
                "Sofía Martínez López" to R.drawable.hijo,
                "Mateo Ramírez Torres" to R.drawable.hijo
            )
        )
    }
}

@Composable
fun ProfileHeader(name: String, email: String, role: String, avatarRes: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.8f))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = Color.LightGray
            ) {
                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = "Avatar",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(
                    text = "Correo: $email",
                    fontSize = 13.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "Rol: $role", fontSize = 13.sp)
            }
        }
    }
}

@Composable
fun GroupSection(groupName: String, students: List<Pair<String, Int>>) {
    var expanded by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.8f), RoundedCornerShape(20.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = groupName,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (expanded) {
            students.forEach { (name, avatar) ->
                ProfileListItem(name = name, avatarRes = avatar)
                Spacer(modifier = Modifier.height(6.dp))
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Expandir",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun ProfileListItem(name: String, avatarRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.9f), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            modifier = Modifier.weight(1f),
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            modifier = Modifier.size(32.dp),
            shape = CircleShape,
            color = Color.LightGray
        ) {
            Image(
                painter = painterResource(id = avatarRes),
                contentDescription = "Avatar",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileTeacherScreenPreview() {
    BAPPTheme {
        ProfileTeacherScreen()
    }
}
