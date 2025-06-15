package com.falconteam.bapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.falconteam.bapp.ui.navigation.AppNavigation
import com.falconteam.bapp.ui.theme.BAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BAPPTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
                // Aquí iría tu NavHost para la navegación
            }
        }
    }
}
