package com.example.gymresultsapp.feature.presentation.exercises_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.gymresultsapp.feature.domain.model.Set

@Composable
fun SetItem(
    num: Int,
    set: Set
) {
    Row(
        modifier = Modifier.background(Color.Black)
    ) {
        Text(
            modifier = Modifier,
            text = num.toString()
        )
        Text(
            modifier = Modifier,
            text = set.weight.toString()
        )
        Text(
            modifier = Modifier,
            text = set.reps.toString()
        )
    }
}