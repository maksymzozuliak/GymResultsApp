package com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymresultsapp.feature.domain.model.toGroup
import com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.components.AddEditSetItem
import com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.components.AddRemoveSetButton
import com.example.gymresultsapp.feature.presentation.utils.MultiplySelector
import com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditExerciseScreen(
    navController: NavController,
    viewModel: AddEditExerciseScreenViewModel = hiltViewModel()
) {
    val nameState = viewModel.exerciseName.value
    val setList = viewModel.exerciseSets.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AddEditExerciseScreenViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditExerciseScreenViewModel.UiEvent.SaveExercise -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditExerciseEvent.SaveExercise)
                },
                backgroundColor = Color.Green,
                modifier = Modifier.padding(bottom = 34.dp)
            )  {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save exercise",
                    tint = Color.White
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(16.dp, top = 46.dp)
        ) {

            val groupList = listOf("Chest", "Back", "Legs", "Biceps", "Triceps", "Shoulders")

            TransparentHintTextField(
                text = nameState.text,
                hint = nameState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditExerciseEvent.EnteredName(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditExerciseEvent.ChangeNameFocus(it))
                },
                isHintVisible = nameState.isHintVisible,
                textStyle = MaterialTheme.typography.h3
            )
            MultiplySelector(options = groupList,
                selectedOption = viewModel.exerciseGroup.value.string,
                onOptionSelect = { option ->
                    viewModel.onEvent(AddEditExerciseEvent.SelectGroup(option.toGroup()))
                }
            )
            LazyColumn(
                modifier = Modifier
            ) {

                itemsIndexed(
                    items = setList,
                    key = { index, item ->
                        item.hashCode()+index
                    }
                ) { index, set ->
                    AddEditSetItem(
                        index = index,
                        reps = set.reps,
                        weight = set.weight,
                        onRepsChanged = {
                            viewModel.onEvent(AddEditExerciseEvent.ChangeReps(index,it))
                        },
                        onWeightChanged = {
                            viewModel.onEvent(AddEditExerciseEvent.ChangeWeight(index,it))
                        },
                    )
                }
            }
            Row {
               AddRemoveSetButton(
                   onClick = { viewModel.onEvent(AddEditExerciseEvent.RemoveSet) },
                   icon = Icons.Default.Remove
               )
               AddRemoveSetButton(
                   onClick = { viewModel.onEvent(AddEditExerciseEvent.AddSet) },
                   icon = Icons.Default.Add
               )
            }
        }
    }
}