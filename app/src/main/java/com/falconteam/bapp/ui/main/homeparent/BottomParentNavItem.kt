package com.falconteam.bapp.ui.main.homeparent

import androidx.annotation.DrawableRes
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.navigation.NavigationRoutes
import com.falconteam.bapp.ui.navigation.navitems.BottomNavItem

enum class BottomParentNavItem(
    override val label: String,
    @DrawableRes override val icon: Int,
    override val destination: NavigationRoutes
): BottomNavItem {
    HOME(
        label = "Inicio",
        icon = R.drawable.ic_home,
        destination = NavigationRoutes.HomeParentDestination.HomeParent
    ),
    CHILD(
        label = "Mi hijo",
        icon = R.drawable.ic_child,
        destination = NavigationRoutes.HomeParentDestination.MyChildren
    ),
    CHAT(
        label = "Mensajes",
        icon = R.drawable.ic_message,
        destination = NavigationRoutes.Chat
    ),
    REPORT(
        label = "Reportes",
        icon = R.drawable.ic_report,
        destination = NavigationRoutes.HomeParentDestination.ReportsParent
    ),
    PROFILE(
        label = "Perfil",
        icon = R.drawable.ic_user,
        destination = NavigationRoutes.Profile
    )
}