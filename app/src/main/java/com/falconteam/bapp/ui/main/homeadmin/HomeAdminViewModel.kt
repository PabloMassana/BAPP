package com.falconteam.bapp.ui.main.homeadmin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.bapp.domain.models.CourseOperation.SEE_STUDENTS
import com.falconteam.bapp.domain.models.CourseOperation.SEE_TEACHER
import com.falconteam.bapp.domain.models.toDomainModel
import com.falconteam.bapp.domain.models.toListSectionItem
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoAlumnosUseCase
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoCursosUseCase
import com.falconteam.bapp.domain.usecases.admin.ObtenerListadoPadresUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeAdminViewModel(
    private val obtenerListadoPadresUseCase: ObtenerListadoPadresUseCase,
    private val obtenerListadoCursosUseCase: ObtenerListadoCursosUseCase,
    private val obtenerListadoAlumnosUseCase: ObtenerListadoAlumnosUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeAdminUiState())
    val uiState: StateFlow<HomeAdminUiState> = _uiState.asStateFlow()

    init {
        onStart()
    }

    fun onStart() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val parentsDeferredResult = async {
                getParentsList()
            }

            val coursesDeferredResult = async {
                getCoursesList()
            }

            val studentsDeferredResult = async {
                getStudentsList()
            }

            awaitAll(parentsDeferredResult, coursesDeferredResult, studentsDeferredResult)

            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun getParentsList() = obtenerListadoPadresUseCase()
        .onSuccess { parents ->
            val parentsList = parents.map { it.toDomainModel() }
            _uiState.update {
                it.copy(
                    parentsList = parentsList.toListSectionItem(
                        onClick = { parentId ->
                            // TODO: Handle parent click action
                            println(parentId)
                        }
                    )
                )
            }
        }.onFailure { _ ->
            _uiState.update { it.copy(parentsList = emptyList()) }
        }


    private suspend fun getCoursesList() = obtenerListadoCursosUseCase()
        .onSuccess { courses ->
            val coursesList = courses.map { it.toDomainModel() }
            _uiState.update {
                it.copy(
                    courseList = coursesList.toListSectionItem(
                        onClick = { course ->
                            when (course) {
                                SEE_STUDENTS -> {
                                    // TODO: Handle see students action
                                }

                                SEE_TEACHER -> {
                                    // TODO: Handle see teacher action
                                }
                            }
                        }
                    )
                )
            }
        }.onFailure { _ ->
            _uiState.update { it.copy(courseList = emptyList()) }
        }

    private suspend fun getStudentsList() = obtenerListadoAlumnosUseCase()
        .onSuccess { students ->
            val studentsList = students.map { it.toDomainModel() }
            _uiState.update {
                it.copy(
                    studentList = studentsList.toListSectionItem(
                        onClick = { studentId ->
                            // TODO: Handle student click action
                        }
                    )
                )
            }
        }.onFailure {
            _uiState.update { it.copy(studentList = emptyList()) }
        }

}