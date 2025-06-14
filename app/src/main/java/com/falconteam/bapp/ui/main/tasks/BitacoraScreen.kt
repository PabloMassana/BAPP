package com.falconteam.bapp.ui.main.tasks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.falconteam.bapp.ui.theme.BAPPTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BitacoraScreen(
    modifier: Modifier = Modifier,
    viewModel: BitacoraViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            // Mostrar loading
        }
        uiState.error != null -> {
            // Mostrar mensaje de error
        }
        else -> {
            // Mostrar lista de bitácoras
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BitacoraScreenPreview() {
    BAPPTheme {
        BitacoraScreen()
    }
}
