package com.falconteam.bapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoutes {
    @Serializable
    data object Login: NavigationRoutes()

    @Serializable
    data object SignUp: NavigationRoutes()

    @Serializable
    data object Home: NavigationRoutes()

    @Serializable
    data object MyChild: NavigationRoutes()

    @Serializable
    data object Reports: NavigationRoutes()

    @Serializable
    data object HomeAdmin: NavigationRoutes()

    @Serializable
    data object HomeTeacher: NavigationRoutes()

    @Serializable
    data object Bitacora: NavigationRoutes()

    @Serializable
    data object Chat: NavigationRoutes()

    @Serializable
    data object Gallery: NavigationRoutes()

    @Serializable
    data object Indicadores: NavigationRoutes()

    @Serializable
    data object Profile: NavigationRoutes()

    @Serializable
    data object Notifications: NavigationRoutes()

    @Serializable
    data object RolSelect: NavigationRoutes()
}