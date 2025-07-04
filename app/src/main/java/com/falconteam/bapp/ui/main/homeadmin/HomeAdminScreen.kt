
package com.falconteam.bapp.ui.main.homeadmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.components.expandablesection.ExpandableSection
import com.falconteam.bapp.ui.theme.BAPPTheme
import org.koin.compose.viewmodel.koinViewModel

//homescreen primera
@Composable
fun HomeAdminScreen(
    viewModel: HomeAdminViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getParentsList()
    }

    HomeAdminContent(uiState = uiState)
}

@Composable
fun HomeAdminContent(
    uiState: HomeAdminUiState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFA07A), Color.White)
                )
            )
            .padding(4.dp)
    ) {
        AdminHeader(title = "Administración", imageRes = R.drawable.hijo)

        Spacer(modifier = Modifier.height(24.dp))

        ExpandableSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            sectionTitle = "Padres registrados",
            sectionItemsShowed = uiState.parentListShowed,
            sectionItemsRemaining = uiState.parentListRemaining,
        )
    }
}

@Composable
fun AdminHeader(title: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.7f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = title,
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
                    painter = painterResource(id = imageRes),
                    contentDescription = "Avatar",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeAdminScreenPreview() {
    BAPPTheme {
        HomeAdminContent(
            uiState = HomeAdminUiState()
        )
    }
}
