package ru.kosproger.data

interface CourseRepository {
    val localDataStore: CourseDatabase
    val remoteDataStore: CourseApiClient
    suspend fun getAllCourses(): List<Course>
}