package com.example.gymresultsapp.feature.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.gymresultsapp.feature.domain.model.Exercise

@Database(
    entities = [Exercise::class],
    version = 1
)
@TypeConverters(DataConverter::class)
abstract class ExerciseDatabase: RoomDatabase() {

    abstract val exerciseDao: ExerciseDao

    companion object {
        const val DATABASE_NAME = "exercise_db"
    }
}