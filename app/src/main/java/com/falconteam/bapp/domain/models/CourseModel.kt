package com.falconteam.bapp.domain.models

import com.falconteam.bapp.data.entity.CourseEntity
import com.falconteam.bapp.ui.components.expandablesection.SectionAction
import com.falconteam.bapp.ui.components.expandablesection.SectionItem

data class CourseModel(
    val id: Int,
    val level: String,
    val idTeacher: Int,
    val courseSection: String
)

enum class CourseOperation {
    SEE_STUDENTS,
    SEE_TEACHER;
}

fun CourseEntity.toDomainModel(): CourseModel = CourseModel(
    id = this.id ?: 0,
    level = this.level ?: "",
    idTeacher = this.idTeacher ?: 0,
    courseSection = this.courseSection.orEmpty()
)

fun CourseModel.toEntityRequest(): CourseEntity = CourseEntity(
    level = this.level,
    courseSection = this.courseSection
)

fun List<CourseModel>.toListSectionItem(
    onClick: (CourseOperation) -> Unit
): List<SectionItem> {
    return this.map { course ->
        SectionItem(
            id = course.id,
            name = "${course.level} - ${course.courseSection}",
            actions = listOf(
                SectionAction(
                    label = "Alumnos",
                    onClick = { onClick(CourseOperation.SEE_STUDENTS) }
                ),
                SectionAction(
                    label = "Docente",
                    onClick = { onClick(CourseOperation.SEE_TEACHER) }
                ),
            )
        )
    }
}