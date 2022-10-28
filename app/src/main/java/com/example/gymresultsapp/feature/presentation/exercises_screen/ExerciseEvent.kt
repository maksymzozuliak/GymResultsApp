package com.example.gymresultsapp.feature.presentation.exercises_screen

import com.example.gymresultsapp.feature.domain.model.Exercise

sealed class ExerciseEvent {
    data class DeleteExercise(val exercise: Exercise): ExerciseEvent()
    data class ChangeDay(val day: String): ExerciseEvent()
    object RestoreExercise : ExerciseEvent()
}