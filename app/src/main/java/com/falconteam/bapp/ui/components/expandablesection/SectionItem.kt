package com.falconteam.bapp.ui.components.expandablesection

typealias IdItem = Int

data class SectionItem(
    val id: Int,
    val name: String,
    val actions: List<SectionAction>
)

data class SectionAction(
    val label: String,
    val onClick: (IdItem) -> Unit
)