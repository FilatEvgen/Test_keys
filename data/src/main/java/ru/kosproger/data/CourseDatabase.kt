package ru.kosproger.data

interface CourseDatabase {
    suspend fun getCourses(): List<Course>
    suspend fun saveCourses(course: List<Course>)
}