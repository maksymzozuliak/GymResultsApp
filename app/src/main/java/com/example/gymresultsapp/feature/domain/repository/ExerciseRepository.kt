package com.example.gymresultsapp.feature.domain.repository

import com.example.gymresultsapp.feature.domain.model.Exercise
import com.example.gymresultsapp.feature.domain.model.Group
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {

    fun getDayExercises(day: Int): Flow<List<Exercise>>

    suspend fun getExerciseById(id: Int): Exercise?

    suspend fun insertExercise(exercise: Exercise)

    suspend fun deleteExercise(exercise: Exercise)
}