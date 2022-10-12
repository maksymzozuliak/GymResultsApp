package com.example.gymresultsapp.feature.presentation.exercises_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gymresultsapp.feature.domain.model.Exercise
import com.example.gymresultsapp.feature.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExercisesScreenViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _list = mutableStateOf(listOf<Exercise>())
    val list: State<List<Exercise>> = _list

    private var getExerciseJob: Job? = null

    private var recentlyDeletedExercise: Exercise? = null

    init {
        getExercises(1)
    }

    fun onEvent(event: ExerciseEvent) {
        when(event) {
            is ExerciseEvent.DeleteExercise ->{
                viewModelScope.launch {
                    useCases.deleteExercise(event.exercise)
                    recentlyDeletedExercise = event.exercise
                }
            }
            is ExerciseEvent.RestoreExercise ->{
                viewModelScope.launch {
                    useCases.addExercise(recentlyDeletedExercise?: return@launch)
                    recentlyDeletedExercise = null
                }
            }
            is ExerciseEvent.ChangeDay ->{
                getExercises(event.day)
            }
        }
    }

    private fun getExercises(day: Int) {
        getExerciseJob?.cancel()
        getExerciseJob = useCases.getDayExercises(day)
            .onEach { exercise ->
                _list.value = exercise
            }
            .launchIn(viewModelScope)
    }
}