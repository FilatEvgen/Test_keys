package ru.kosproger.domain.models

import ru.kosproger.data.Course

interface CourseRepository {
    suspend fun getAllCourses(): List<Course>
}