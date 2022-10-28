package com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.gymresultsapp.feature.domain.model.toGroup
import com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.components.AddEditSetItem
import com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.components.AddRemoveSetButton
import com.example.gymresultsapp.feature.presentation.utils.MultiplySelector
import com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import kotlin.random.Random

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
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 34.dp)
            )  {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save exercise",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 38.dp)
        ) {

            val groupList = listOf("Chest", "Back", "Legs", "Biceps", "Triceps", "Shoulders")
            MultiplySelector(
                options = groupList,
                selectedOption = viewModel.exerciseGroup.value.string,
                onOptionSelect = { option ->
                    viewModel.onEvent(AddEditExerciseEvent.SelectGroup(option.toGroup()))
                },
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = 12.dp)
            )
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
                modifier = Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 6.dp
                )
            )
            Divider(
                color = MaterialTheme.colors.primary,
                thickness = 3.dp,
                modifier = Modifier.padding(horizontal = 64.dp)
            )
            Box(modifier = Modifier
                .padding(
                    top = 20.dp,
                    start = 12.dp,
                    end = 12.dp,
                    bottom = 6.dp
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colors.secondary)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colors.primary)
                        .padding(vertical = 8.dp)
                ) {

                    itemsIndexed(
                        items = setList,
                        key = { index, item ->
                            item.hashCode() - index
                        }
                    ) { index, set ->
                        AddEditSetItem(
                            index = index,
                            reps = set.reps,
                            weight = set.weight,
                            onRepsChanged = {
                                viewModel.onEvent(AddEditExerciseEvent.ChangeReps(index, it))
                            },
                            onWeightChanged = {
                                viewModel.onEvent(AddEditExerciseEvent.ChangeWeight(index, it))
                            },
                        )
                        if (index != setList.size-1) {
                            Divider(
                                color = MaterialTheme.colors.secondary,
                                thickness = 3.dp,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
               AddRemoveSetButton(
                   onClick = { viewModel.onEvent(AddEditExerciseEvent.RemoveSet) },
                   icon = Icons.Default.Remove
               )
                Spacer(modifier = Modifier.width(20.dp))
               AddRemoveSetButton(
                   onClick = { viewModel.onEvent(AddEditExerciseEvent.AddSet) },
                   icon = Icons.Default.Add
               )
            }
        }
    }
}