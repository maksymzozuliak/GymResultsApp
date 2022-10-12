package com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen

import androidx.compose.ui.focus.FocusState
import com.example.gymresultsapp.feature.domain.model.Group

sealed class AddEditExerciseEvent {
    data class EnteredName(val value: String): AddEditExerciseEvent()
    data class ChangeNameFocus(val focus: FocusState): AddEditExerciseEvent()
    data class SelectGroup(val group: Group): AddEditExerciseEvent()
    data class ChangeWeight(val index: Int, val weight: String): AddEditExerciseEvent()
    data class ChangeReps(val index: Int, val reps: Int): AddEditExerciseEvent()
    object AddSet: AddEditExerciseEvent()
    object RemoveSet: AddEditExerciseEvent()
    object SaveExercise: AddEditExerciseEvent()
}