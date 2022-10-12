package com.example.gymresultsapp.feature.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    val group: Group,
    val day: Int,
    val name: String,
    val sets: List<Set>,
    @PrimaryKey val id: Int? = null
)

enum class Group(val color : Color, val day: Int, val string: String) {
    CHEST(Color.Blue, 1, "Chest"),
    BACK(Color.Green, 2, "Back"),
    LEGS(Color.Gray, 3, "Legs"),
    BICEPS(Color.Cyan, 1, "Biceps"),
    TRICEPS(Color.Magenta, 2, "Triceps"),
    SHOULDERS(Color.Red, 3, "Shoulders");
}

fun String.toGroup() : Group {
    return when(this) {
        "Chest" -> Group.CHEST
        "Back" -> Group.BACK
        "Legs" -> Group.LEGS
        "Biceps" -> Group.BICEPS
        "Triceps" -> Group.TRICEPS
        "Shoulders" -> Group.SHOULDERS
        else -> Group.CHEST
    }
}

data class Set(
    val weight: String,
    val reps: Int
)
