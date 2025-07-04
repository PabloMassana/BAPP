package com.falconteam.bapp.ui.main.homeadmin

import com.falconteam.bapp.ui.components.expandablesection.SectionItem

data class HomeAdminUiState(
    val parentsList: List<SectionItem> = emptyList(),
    val courseList: List<SectionItem> = emptyList(),
    val studentList: List<SectionItem> = emptyList(),
    val isLoading: Boolean = false,
) {
    val parentListShowed = parentsList.take(3)
    val parentListRemaining = parentsList.drop(3)

    val courseListShowed = courseList.take(3)
    val courseListRemaining = courseList.drop(3)

    val studentListShowed = studentList.take(3)
    val studentListRemaining = studentList.drop(3)
}
