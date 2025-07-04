package com.falconteam.bapp.ui.main.aprobaradmin

import com.falconteam.bapp.domain.models.ParentModel

data class AprobarAdminUiState(
    val searchQuery: String = "",
    val parentsList: List<ParentModel> = emptyList()
)
