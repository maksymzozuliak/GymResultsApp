package com.example.gymresultsapp.feature.data.repository

import com.example.gymresultsapp.feature.data.data_source.ExerciseDao
import com.example.gymresultsapp.feature.domain.model.Exercise
import com.example.gymresultsapp.feature.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class ExerciseRepositoryImplementation(
    private val dao: ExerciseDao
): ExerciseRepository {
    override fun getExercises(): Flow<List<Exercise>> {
        return dao.getExercises()
    }

    override suspend fun getExerciseById(id: Int): Exercise? {
        return dao.getExerciseById(id)
    }

    override suspend fun insertExercise(exercise: Exercise) {
        return dao.insertExercise(exercise)
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        return dao.deleteExercise(exercise)
    }

}