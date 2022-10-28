package com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymresultsapp.feature.domain.model.Exercise
import com.example.gymresultsapp.feature.domain.model.Group
import com.example.gymresultsapp.feature.domain.model.Set
import com.example.gymresultsapp.feature.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddEditExerciseScreenViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _exerciseName = mutableStateOf(ExerciseTextFieldState(
        hint = "Enter name"
    ))
    val exerciseName: State<ExerciseTextFieldState> = _exerciseName

    private val _exerciseGroup = mutableStateOf(Group.CHEST)
    val exerciseGroup: State<Group> = _exerciseGroup

    private val _exerciseSets = mutableStateOf(listOf<Set>(Set("10" ,10)))
    val exerciseSets: State<List<Set>> = _exerciseSets

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentExerciseId: Int? = null

    init {
        savedStateHandle.get<Int>("exerciseId")?.let { exerciseId ->
            if(exerciseId != -1) {
                viewModelScope.launch {
                    useCases.getExercise(exerciseId)?.also { exercise ->
                        currentExerciseId = exercise.id
                        _exerciseName.value = exerciseName.value.copy(
                            text = exercise.name,
                            isHintVisible = false
                        )
                        _exerciseSets.value = exercise.sets
                        _exerciseGroup.value = exercise.group
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: AddEditExerciseEvent) {
        when(event) {
            is AddEditExerciseEvent.EnteredName -> {
                _exerciseName.value = exerciseName.value.copy(
                    text = event.value
                )
            }
            is AddEditExerciseEvent.ChangeNameFocus -> {
                _exerciseName.value = exerciseName.value.copy(
                    isHintVisible = !event.focus.isFocused &&
                            exerciseName.value.text.isBlank()
                )
            }
            is AddEditExerciseEvent.SaveExercise -> {
                viewModelScope.launch {
                    useCases.addExercise(
                        Exercise(
                            name = exerciseName.value.text,
                            id = currentExerciseId,
                            day = exerciseGroup.value.day,
                            sets = exerciseSets.value,
                            group = exerciseGroup.value
                        )
                    )
                    _eventFlow.emit(UiEvent.SaveExercise)
                }
            }
            is AddEditExerciseEvent.SelectGroup -> {
                _exerciseGroup.value = event.group
            }
            is AddEditExerciseEvent.AddSet -> {
                val newList = mutableListOf<Set>()
                newList.addAll(_exerciseSets.value)
                newList.add(Set("10", 10))
                _exerciseSets.value = newList
            }
            is AddEditExerciseEvent.RemoveSet -> {
                val newList = mutableListOf<Set>()
                newList.addAll(_exerciseSets.value)
                newList.removeLast()
                _exerciseSets.value = newList
            }
            is AddEditExerciseEvent.ChangeReps -> {
                val newList = mutableListOf<Set>()
                newList.addAll(_exerciseSets.value)
                newList[event.index] = newList[event.index].copy(reps = event.reps)
                _exerciseSets.value = newList
            }
            is AddEditExerciseEvent.ChangeWeight -> {
                val newList = mutableListOf<Set>()
                newList.addAll(_exerciseSets.value)
                newList[event.index] = newList[event.index].copy(weight = event.weight)
                _exerciseSets.value = newList
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveExercise: UiEvent()
    }
}