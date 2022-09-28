package com.example.gymresultsapp.feature.domain.use_case

import com.example.gymresultsapp.feature.domain.model.Exercise
import com.example.gymresultsapp.feature.domain.repository.ExerciseRepository

class DeleteExercise (
    private val repository: ExerciseRepository
) {

    suspend operator fun invoke(exercise: Exercise) {

        repository.deleteExercise(exercise)
    }

}