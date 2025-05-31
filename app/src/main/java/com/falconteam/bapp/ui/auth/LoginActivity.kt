package com.falconteam.bapp.ui.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.falconteam.bapp.ui.theme.BAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BAppTheme {
                // Aquí iría tu pantalla de inicio de sesión
            }
        }
    }
}
