package com.falconteam.bapp.ui.navigation.navitems

import androidx.annotation.DrawableRes
import com.falconteam.bapp.ui.navigation.NavigationRoutes

interface BottomNavItem {
    val destination: NavigationRoutes
    @get:DrawableRes
    val icon: Int
    val label: String
}