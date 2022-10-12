package com.example.gymresultsapp.feature.presentation.utils

sealed class Screen(val route: String) {
    object ExercisesScreen: Screen("exercises_screen")
    object AddEditExerciseScreen: Screen("add_edit_exercise_screen")
}
