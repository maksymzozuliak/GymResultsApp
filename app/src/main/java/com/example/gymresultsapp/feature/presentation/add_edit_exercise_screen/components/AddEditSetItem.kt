package com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gymresultsapp.ui.theme.blueGray

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddEditSetItem(
    index: Int,
    reps: Int,
    weight: String,
    modifier: Modifier = Modifier,
    onRepsChanged: (Int) -> Unit,
    onWeightChanged: (String) -> Unit,
) {
    val weightItems = List(43) { i ->
        if (i < 10)"${i.plus(1)}"
        else if (i <= 27)"${i.minus(7).times(5)}"
        else "${i.minus(17).times(10)}"
    }
    var weightExpanded by remember { mutableStateOf(false) }
    var weightSelected by remember { mutableStateOf(weight) }
    var smallWeightExpanded by remember { mutableStateOf(false) }
    val smallWeightItems = listOf("0","1.25","2.5")
    var repsExpanded by remember { mutableStateOf(false) }
    val repsItems = List(20) { i -> i + 1}
    var repsSelected by remember { mutableStateOf(reps) }
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = (index + 1).toString(),
                style = MaterialTheme.typography.subtitle1,
            )
            Spacer(modifier = Modifier
                .width(36.dp))
            Row(
                modifier = Modifier
                    .combinedClickable(
                        onClick = { weightExpanded = true },
                        onLongClick = { smallWeightExpanded = true }
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colors.primaryVariant)
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            ) {
                Text(
                    text = weightSelected,
                    style = MaterialTheme.typography.body2,
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    modifier = Modifier
                        .padding(top = 6.dp),
                    style = MaterialTheme.typography.subtitle2,
                    text = "kg."
                )
            }
            DropdownMenu(
                expanded = weightExpanded,
                onDismissRequest = { weightExpanded = false },
                offset = DpOffset(60.dp, 0.dp),
                modifier = Modifier.height(400.dp)
            ) {
                weightItems.forEach { n ->
                    DropdownMenuItem(
                        onClick = {
                        weightSelected = n
                        weightExpanded = false
                        onWeightChanged(n)
                    },
                        modifier = Modifier
                            .background(
                                if (n.toDouble()-weightSelected.toDouble() in -2.5..0.0 && n.toDouble() > 10) MaterialTheme.colors.secondary
                                else if (n == weightSelected) MaterialTheme.colors.secondary
                                else MaterialTheme.colors.primaryVariant
                            )
                    ) {
                        Text(text = n)
                    }
                }
            }
            DropdownMenu(
                modifier = Modifier
                    .background(MaterialTheme.colors.primaryVariant),
                expanded = smallWeightExpanded,
                onDismissRequest = { smallWeightExpanded = false },
                offset = DpOffset(60.dp, 0.dp)
            ) {
                smallWeightItems.forEach { n ->
                    DropdownMenuItem(onClick = {
                        smallWeightExpanded = false
                        onWeightChanged((weightSelected.toDouble() + n.toDouble()).toString())
                    }) {
                        Text(text = n)
                    }
                }
            }
            Spacer(modifier = Modifier
                .weight(1.0f))
            Text(
                text = repsSelected.toString(),
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .clickable(onClick = { repsExpanded = true })
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colors.primaryVariant)
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            )
            DropdownMenu(
                expanded = repsExpanded,
                onDismissRequest = { repsExpanded = false },
                offset = DpOffset(277.dp, 0.dp),
                modifier = Modifier.height(400.dp).width(66.dp)
            ) {
                repsItems.forEach { n ->
                    DropdownMenuItem(
                        onClick = {
                        repsSelected = n
                        repsExpanded = false
                        onRepsChanged(n)
                    },
                        modifier = Modifier
                            .background(
                            if (n == repsSelected) MaterialTheme.colors.secondary
                            else MaterialTheme.colors.primaryVariant
                            )
                    )
                    {
                        Text(text = n.toString())
                    }
                }
            }
        }
    }
}