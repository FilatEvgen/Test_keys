package ru.kosproger.domain.models

import ru.kosproger.data.Course
import ru.kosproger.data.CourseRepository

class CourseUseCase(private val courseRepository: CourseRepository) {
    suspend fun execute(): List<Course> {
        return courseRepository.getAllCourses()
    }
}