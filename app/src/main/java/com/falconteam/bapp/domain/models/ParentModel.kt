package com.falconteam.bapp.domain.models

import com.falconteam.bapp.data.entity.ParentEntity
import com.falconteam.bapp.ui.components.expandablesection.SectionAction
import com.falconteam.bapp.ui.components.expandablesection.SectionItem

data class ParentModel(
    val id: Int,
    val name: String,
    val lastname: String,
    val userId: String
)

fun ParentEntity.toDomainModel() = ParentModel(
    id = id ?: 0,
    name = name.orEmpty(),
    lastname = lastname.orEmpty(),
    userId = userId.orEmpty()
)

fun List<ParentModel>.toListSectionItem(
    onClick: (Int) -> Unit = {}
): List<SectionItem> {
    return this.map { parent ->
        SectionItem(
            id = parent.id,
            name = "${parent.name} ${parent.lastname}",
            actions = listOf(
                SectionAction(
                    label = "Asignar hijo",
                    onClick = onClick
                )
            )
        )
    }
}