package com.falconteam.bapp.ui.main.homeadmin

import com.falconteam.bapp.ui.components.expandablesection.SectionItem

data class HomeAdminUiState(
    val parentsList: List<SectionItem> = emptyList(),
    val isLoading: Boolean = false,
) {
    val parentListShowed = parentsList.take(2)
    val parentListRemaining = parentsList.drop(2)
}
