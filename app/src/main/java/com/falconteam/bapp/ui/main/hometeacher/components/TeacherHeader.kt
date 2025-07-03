package com.falconteam.bapp.ui.main.hometeacher.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.falconteam.bapp.R

@Composable
fun TeacherHeader(name: String, avatarUrl: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x99FFFFFF))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = CircleShape,
                modifier = Modifier.size(64.dp)
            ) {
                val painter = if (avatarUrl.isNullOrBlank()) {
                    painterResource(id = R.drawable.parent)
                } else {
                    rememberAsyncImagePainter(avatarUrl)
                }

                Image(
                    painter = painter,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Bienvenido, docente:",
                fontSize = 13.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeacherHeaderPreviewWithUrl() {
    TeacherHeader(
        name = "Brandon Cornejo",
        avatarUrl = "https://placekitten.com/200/200"
    )
}

@Preview(showBackground = true)
@Composable
fun TeacherHeaderPreviewWithoutUrl() {
    TeacherHeader(
        name = "Brandon Cornejo",
        avatarUrl = null
    )
}
