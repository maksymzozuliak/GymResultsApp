package com.example.gymresultsapp.feature.domain.use_case

import com.example.gymresultsapp.feature.domain.model.Exercise
import com.example.gymresultsapp.feature.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDayExercises (
    private val repository: ExerciseRepository
) {

    operator fun invoke(day: Int): Flow<List<Exercise>> {
        return repository.getDayExercises(day).map { exercise ->
            exercise.sortedBy { !it.group.primary }
        }
    }

}