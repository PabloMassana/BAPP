package com.falconteam.bapp.ui.main.homeparent

import androidx.annotation.DrawableRes
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.navigation.NavigationRoutes

enum class BottomParentNavItem(
    val label: String,
    @DrawableRes val icon: Int,
    val destination: NavigationRoutes
) {
    HOME(
        label = "Inicio",
        icon = R.drawable.ic_home,
        destination = NavigationRoutes.Home
    ),
    CHILD(
        label = "Mi hijo",
        icon = R.drawable.ic_child,
        destination = NavigationRoutes.MyChild
    ),
    CHAT(
        label = "Mensajes",
        icon = R.drawable.ic_message,
        destination = NavigationRoutes.Chat
    ),
    REPORT(
        label = "Reportes",
        icon = R.drawable.ic_report,
        destination = NavigationRoutes.Reports
    ),
    PROFILE(
        label = "Perfil",
        icon = R.drawable.ic_user,
        destination = NavigationRoutes.Profile
    )
}