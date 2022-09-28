package com.example.gymresultsapp.feature.domain.use_case

data class UseCases(
    val getDayExercises: GetDayExercises,
    val getExercise: GetExercise,
    val deleteExercise: DeleteExercise,
    val addExercise: AddExercise
)