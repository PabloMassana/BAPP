package com.falconteam.bapp.ui.main.homeadmin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun HomeAdminScreen(
    viewModel: HomeAdminViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeAdminContent(uiState) {
        viewModel.onStart()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAdminContent(
    uiState: HomeAdminUiState,
    onRefreshAction: () -> Unit = {}
) {
    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = { onRefreshAction() },
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFFFFA07A), Color.White)
                    )
                ),
            contentPadding = PaddingValues(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item{
                AdminHeader(name = "Administración", avatarRes = R.drawable.parent)
            }

            item {
                ExpandableSection(
                    modifier = Modifier.fillMaxWidth(),
                    sectionTitle = "Grupos existentes",
                    sectionItemsShowed = uiState.courseListShowed,
                    sectionItemsRemaining = uiState.courseListRemaining,
                    isLoading = uiState.isLoading
                )
            }

            item {
                ExpandableSection(
                    modifier = Modifier.fillMaxWidth(),
                    sectionTitle = "Alumnos registrados",
                    sectionItemsShowed = uiState.studentListShowed,
                    sectionItemsRemaining = uiState.studentListRemaining,
                    isLoading = uiState.isLoading
                )
            }

            item {
                ExpandableSection(
                    modifier = Modifier.fillMaxWidth(),
                    sectionTitle = "Padres registrados",
                    sectionItemsShowed = uiState.parentListShowed,
                    sectionItemsRemaining = uiState.parentListRemaining,
                    isLoading = uiState.isLoading
                )
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun AdminHeader(name: String, avatarRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeAdminScreenPreview() {
    BAPPTheme {
        HomeAdminContent(
            uiState = HomeAdminUiState()
        )
    }
}
