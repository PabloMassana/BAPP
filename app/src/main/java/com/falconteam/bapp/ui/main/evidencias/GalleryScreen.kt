package com.falconteam.bapp.ui.main.evidencias

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.falconteam.bapp.ui.theme.BAPPTheme

@Composable
fun GalleryScreen(
    uiState: GaleriaUiState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.isLoading -> {
            // loading UI
        }
        uiState.error != null -> {
            // error UI
        }
        else -> {
            // muestra la lista de evidencias
        }
    }
}


@Preview
@Composable
private fun GalleryScreenPreview() {
    BAPPTheme {
        GalleryScreen()
    }
}