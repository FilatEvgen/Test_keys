//package ru.kosproger.data
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//
//@Dao
//interface CourseDao : CourseDatabase {
//    @Query("SELECT * FROM courses")
//    override suspend fun getCourses(): List<Course>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    override suspend fun saveCourses(course: List<Course>)
//}