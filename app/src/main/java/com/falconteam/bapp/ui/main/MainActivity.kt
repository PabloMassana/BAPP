package com.falconteam.bapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.falconteam.bapp.ui.theme.BAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BAppTheme {
                // Aquí iría tu NavHost para la navegación
            }
        }
    }
}
