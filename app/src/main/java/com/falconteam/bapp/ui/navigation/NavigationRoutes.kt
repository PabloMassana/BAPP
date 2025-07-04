package com.falconteam.bapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface NavigationRoutes {
    @Serializable
    data object Login: NavigationRoutes

    @Serializable
    data object SignUp: NavigationRoutes

    @Serializable
    sealed class HomeParentDestination: NavigationRoutes {
        @Serializable
        data object HomeParentNavGraph: HomeParentDestination()

        @Serializable
        data object HomeParent: HomeParentDestination()

        @Serializable
        data object MyChildren: HomeParentDestination()

        @Serializable
        data object ReportsParent: HomeParentDestination()
    }

    @Serializable
    sealed class HomeAdminDestination: NavigationRoutes {
        @Serializable
        data object HomeAdminNavGraph: HomeAdminDestination()

        @Serializable
        data object HomeAdmin: HomeAdminDestination()

        @Serializable
        data object DeleteAdmin: HomeAdminDestination()

        @Serializable
        data object TeachersManagementAdmin: HomeAdminDestination()
    }

    @Serializable
    sealed class HomeTeacherDestination: NavigationRoutes {
        @Serializable
        data object HomeTeacherNavGraph: HomeTeacherDestination()

        @Serializable
        data object HomeTeacher: HomeTeacherDestination()

        @Serializable
        data object ReportsTeacher: HomeTeacherDestination()

        @Serializable
        data object AddActivities: HomeTeacherDestination()
    }

    @Serializable
    data object Bitacora: NavigationRoutes

    @Serializable
    data object Chat: NavigationRoutes

    @Serializable
    data object Gallery: NavigationRoutes

    @Serializable
    data object Indicadores: NavigationRoutes

    @Serializable
    data object Profile: NavigationRoutes

    @Serializable
    data object Notifications: NavigationRoutes

    @Serializable
    data object RolSelect: NavigationRoutes
}