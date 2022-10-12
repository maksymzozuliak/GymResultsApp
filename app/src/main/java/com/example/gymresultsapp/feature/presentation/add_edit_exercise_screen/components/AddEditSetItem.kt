package com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun AddEditSetItem(
    index: Int,
    reps: Int,
    weight: String,
    modifier: Modifier = Modifier,
    onRepsChanged: (Int) -> Unit,
    onWeightChanged: (String) -> Unit
) {
    var weightExpanded by remember { mutableStateOf(false) }
    val weightItems = listOf("1", "2", "3", "4", "5", "6")
    var weightSelected by remember { mutableStateOf(weight) }
    var repsExpanded by remember { mutableStateOf(false) }
    val repsItems = listOf(1, 2, 3, 4, 5, 6)
    var repsSelected by remember { mutableStateOf(reps) }
    Box(
        modifier = modifier
    ) {
        Row {
            Text(
                text = (index + 1).toString(),
                style = MaterialTheme.typography.body1,
                color = Color.Red,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = weightSelected,
                style = MaterialTheme.typography.body1,
                color = Color.Red,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable(onClick = { weightExpanded = true })
            )
            DropdownMenu(
                expanded = weightExpanded,
                onDismissRequest = { weightExpanded = false },
                modifier = Modifier.fillMaxWidth().background(Color.Red)
            ) {
                weightItems.forEach { n ->
                    DropdownMenuItem(onClick = {
                        weightSelected = n
                        weightExpanded = false
                        onWeightChanged(n)
                    }) {
                        Text(text = n)
                    }
                }
            }
            Text(
                text = repsSelected.toString(),
                style = MaterialTheme.typography.body1,
                color = Color.Red,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable(onClick = { repsExpanded = true })
            )
            DropdownMenu(
                expanded = repsExpanded,
                onDismissRequest = { repsExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                repsItems.forEach { n ->
                    DropdownMenuItem(onClick = {
                        repsSelected = n
                        repsExpanded = false
                        onRepsChanged(n)
                    }) {
                        Text(text = n.toString())
                    }
                }
            }
        }
    }
}