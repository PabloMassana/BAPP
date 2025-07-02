package com.falconteam.bapp.ui.main.homeadmin

import androidx.annotation.DrawableRes
import com.falconteam.bapp.R
import com.falconteam.bapp.ui.navigation.NavigationRoutes
import com.falconteam.bapp.ui.navigation.navitems.BottomNavItem

enum class BottomAdminNavItem(
    override val label: String,
    @DrawableRes override val icon: Int,
    override val destination: NavigationRoutes.HomeAdminDestination
): BottomNavItem {
    ADD(
        label = "Agregar",
        icon = R.drawable.ic_add,
        destination = NavigationRoutes.HomeAdminDestination.HomeAdmin
    ),
    DELETE(
        label = "Borrar",
        icon = R.drawable.ic_trash,
        destination = NavigationRoutes.HomeAdminDestination.DeleteAdmin
    ),
    TEACHERS(
        label = "Docentes",
        icon = R.drawable.ic_user,
        destination = NavigationRoutes.HomeAdminDestination.TeachersManagementAdmin
    )
}