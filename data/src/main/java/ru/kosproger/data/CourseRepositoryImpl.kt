package ru.kosproger.data

class CourseRepositoryImpl (
    override val localDataStore: CourseDatabase,
    override val remoteDataStore: CourseApiClient
) : CourseRepository {
    override suspend fun getAllCourses(): List<Course> {
        val localCourses = localDataStore.getCourses()
        return if (localCourses.isNotEmpty()) {
            localCourses
        } else {
            val remoteResponse = remoteDataStore.fetchCourses()
            localDataStore.saveCourses(remoteResponse.courses)
            remoteResponse.courses
        }
    }
}