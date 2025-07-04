package com.falconteam.bapp.ui.main.hometeacher

import androidx.annotation.DrawableRes
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.navigation.NavigationRoutes
import com.falconteam.bapp.ui.navigation.navitems.BottomNavItem

enum class BottomTeacherNavItem(
    override val label: String,
    @DrawableRes override val icon: Int,
    override val destination: NavigationRoutes
) : BottomNavItem {
    HOME(
        label = "Inicio",
        icon = R.drawable.ic_home,
        destination = NavigationRoutes.HomeTeacherDestination.HomeTeacher
    ),
    REPORTS(
        label = "Borrar",
        icon = R.drawable.ic_trash,
        destination = NavigationRoutes.HomeTeacherDestination.ReportsTeacher
    ),
    ADD(
        label = "Agregar",
        icon = R.drawable.ic_add,
        destination = NavigationRoutes.HomeTeacherDestination.AddActivities
    ),
    CHAT(
        label = "Mensajes",
        icon = R.drawable.ic_message,
        destination = NavigationRoutes.Chat
    ),
    PROFILE(
        label = "Perfil",
        icon = R.drawable.ic_user,
        destination = NavigationRoutes.Profile
    )
}
