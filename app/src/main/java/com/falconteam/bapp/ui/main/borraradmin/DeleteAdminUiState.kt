package com.falconteam.bapp.ui.main.borraradmin

import com.falconteam.bapp.domain.models.CourseModel
import com.falconteam.bapp.domain.models.StudentModel

data class DeleteAdminUiState(
    val courses: List<CourseModel> = emptyList(),
    val selectedCourse: CourseModel? = null,
    val isLoading: Boolean = false,
    val studentsCourseSelected: List<StudentModel> = emptyList(),
    val expandedDropdown: Boolean = false,
    val searchQuery: String = "",
)
