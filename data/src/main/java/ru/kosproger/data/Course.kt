package ru.kosproger.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class Course(
    @PrimaryKey val id: String,
    val title: String,
    val text: String,
    val price: Double,
    val rate: Float,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)