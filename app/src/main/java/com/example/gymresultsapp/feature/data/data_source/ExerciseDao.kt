package com.example.gymresultsapp.feature.data.data_source

import androidx.room.*
import com.example.gymresultsapp.feature.domain.model.Exercise
import com.example.gymresultsapp.feature.domain.model.Group
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercise WHERE `day` = :day")
    fun getDayExercises(day: Int): Flow<List<Exercise>>

    @Query("SELECT * FROM exercise WHERE id = :id")
    suspend fun getExerciseById(id: Int): Exercise?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)
}