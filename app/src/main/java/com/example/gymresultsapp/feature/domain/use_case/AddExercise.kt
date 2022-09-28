package com.example.gymresultsapp.feature.domain.use_case

import com.example.gymresultsapp.feature.domain.model.Exercise
import com.example.gymresultsapp.feature.domain.repository.ExerciseRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddExercise(
    private val repository: ExerciseRepository
) {

    suspend operator fun invoke(exercise: Exercise) {

        repository.insertExercise(exercise)

    }
}