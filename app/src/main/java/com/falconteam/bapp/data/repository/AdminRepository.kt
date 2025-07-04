package com.falconteam.bapp.data.repository

import com.falconteam.bapp.data.entity.CourseEntity
import com.falconteam.bapp.data.entity.ParentEntity
import com.falconteam.bapp.data.entity.StudentCourseEntity
import com.falconteam.bapp.data.entity.StudentEntity
import com.falconteam.bapp.data.entity.TeacherEntity
import com.falconteam.bapp.data.local.DataStoreHelper
import com.falconteam.bapp.data.supabase.SupabaseManager
import com.falconteam.bapp.utils.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AdminRepository {
    suspend fun getAllParents(): Result<List<ParentEntity>>
    suspend fun getAllCourses(): Result<List<CourseEntity>>
    suspend fun getAllStudents(): Result<List<StudentEntity>>
    suspend fun insertCourse(courseEntity: CourseEntity): Result<CourseEntity>
    suspend fun approveParentAsTeacher(userId: String, parentId: Int): Result<Unit>
    suspend fun insertTeacher(teacherEntity: TeacherEntity): Result<TeacherEntity>

    suspend fun insertStudentWithCourse(studentEntity: StudentEntity, courseId: Int): Result<StudentEntity>
}

class AdminRepositoryImpl(
    private val supabaseManager: SupabaseManager,
    private val dataStoreHelper: DataStoreHelper
) : AdminRepository {

    override suspend fun getAllParents(): Result<List<ParentEntity>> = withContext(Dispatchers.IO) {
        try {
            val parents = supabaseManager.obtenerListadoPadres()
            Result.success(parents)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getAllCourses(): Result<List<CourseEntity>> = withContext(Dispatchers.IO) {
        try {
            val courses = supabaseManager.obtenerGrupos()
            Result.success(courses)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getAllStudents(): Result<List<StudentEntity>> =
        withContext(Dispatchers.IO) {
            try {
                val students = supabaseManager.obtenerAlumnos()
                Result.success(students)
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            }
        }

    override suspend fun insertCourse(courseEntity: CourseEntity): Result<CourseEntity> =
        withContext(Dispatchers.IO) {
            try {
                val insertedCourse = supabaseManager.insertarGrupo(courseEntity)
                Result.success(insertedCourse)
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            }
        }

    override suspend fun approveParentAsTeacher(
        userId: String,
        parentId: Int
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            // Assuming that approving a parent as a teacher involves updating their role
            supabaseManager.actualizarRolUsuario(userId, UserRole.TEACHER.name)
            val parentRemoved = supabaseManager.eliminarPadre(parentId)
            val newTeacher = supabaseManager.insertarMaestro(
                TeacherEntity(
                    userId = userId,
                    name = parentRemoved.name,
                    lastname = parentRemoved.lastname
                )
            )

            println("Teacher approved: $newTeacher")

            Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTeacher(teacherEntity: TeacherEntity): Result<TeacherEntity> =
        withContext(Dispatchers.IO) {
            try {
                val insertedTeacher = supabaseManager.insertarMaestro(teacherEntity)
                Result.success(insertedTeacher)
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            }
        }

    override suspend fun insertStudentWithCourse(studentEntity: StudentEntity, courseId: Int): Result<StudentEntity> =
        withContext(Dispatchers.IO) {
            try {
                val insertedStudent = supabaseManager.insertarAlumno(studentEntity)
                supabaseManager.insertarAlumnoCurso(
                    StudentCourseEntity(
                        idStudent = insertedStudent.id,
                        idCourse = courseId
                    )
                )

                Result.success(insertedStudent)
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            }
        }
}