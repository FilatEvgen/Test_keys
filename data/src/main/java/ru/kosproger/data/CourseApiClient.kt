package ru.kosproger.data

import retrofit2.http.GET

interface CourseApiClient {
    @GET("uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun fetchCourses(): CourseResponse
}