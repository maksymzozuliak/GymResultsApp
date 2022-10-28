package com.example.gymresultsapp.feature.presentation.add_edit_exercise_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AddRemoveSetButton(
    onClick: () -> Unit,
    icon: ImageVector
) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        modifier = Modifier
            .size(50.dp)
    )  {
        Icon(
            imageVector = icon,
            contentDescription = "Change sets",
            tint = Color.White
        )
    }
}