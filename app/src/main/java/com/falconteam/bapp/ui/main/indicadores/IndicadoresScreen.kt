package com.falconteam.bapp.ui.main.indicadores

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.falconteam.bapp.data.models.Indicador
import com.falconteam.bapp.ui.theme.BAPPTheme

@Composable
fun IndicadoresScreen(
    state: IndicadoresUiState,
    onAddIndicador: (Indicador) -> Unit,
    modifier: Modifier = Modifier
) {
    // mostrar una lista de indicadores
    // y un botón para añadir uno nuevo
}


@Preview
@Composable
private fun IndicadoresScreenPreview() {
    BAPPTheme {
        IndicadoresScreen(
            state = IndicadoresUiState(),
            onAddIndicador = {}
        )
    }
}
