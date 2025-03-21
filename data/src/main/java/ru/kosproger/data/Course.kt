package ru.kosproger.data

//@Entity(tableName = "courses")
data class Course(
    val id: String,
    val title: String,
    val text: String,
    val price: Double,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)