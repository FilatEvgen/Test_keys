package ru.kosproger.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://drive.usercontent.google.com/u/0/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val courseApiClient: CourseApiClient by lazy {
        retrofit.create(CourseApiClient::class.java)
    }
}