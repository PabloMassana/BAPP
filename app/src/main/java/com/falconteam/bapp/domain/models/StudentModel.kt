package com.falconteam.bapp.domain.models

import com.falconteam.bapp.data.entity.StudentEntity
import com.falconteam.bapp.ui.components.expandablesection.SectionAction
import com.falconteam.bapp.ui.components.expandablesection.SectionItem

data class StudentModel(
    val id: Int,
    val name: String,
    val lastName: String,
    val birthDate: String
)

fun StudentEntity.toDomainModel() = StudentModel(
    id = this.id ?: 0,
    name = this.name.orEmpty(),
    lastName = this.lastName.orEmpty(),
    birthDate = this.birthDate.orEmpty()
)

fun StudentModel.toEntity() = StudentEntity(
    name = this.name,
    lastName = this.lastName
)

fun List<StudentModel>.toListSectionItem(
    onClick: (Int) -> Unit
): List<SectionItem> {
    return this.map { student ->
        SectionItem(
            id = student.id,
            name = "${student.name} ${student.lastName}",
            actions = listOf(
                SectionAction(
                    label = "Asignar encargado",
                    onClick = onClick
                )
            )
        )
    }
}