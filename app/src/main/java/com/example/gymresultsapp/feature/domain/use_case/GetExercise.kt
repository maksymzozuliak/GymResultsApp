package com.example.gymresultsapp.feature.domain.use_case

import com.example.gymresultsapp.feature.domain.model.Exercise
import com.example.gymresultsapp.feature.domain.repository.ExerciseRepository

class GetExercise (
    private val repository: ExerciseRepository
) {

    suspend operator fun invoke(id: Int): Exercise? {
        return repository.getExerciseById(id)
    }
}