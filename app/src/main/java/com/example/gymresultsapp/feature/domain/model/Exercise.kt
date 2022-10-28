package com.example.gymresultsapp.feature.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gymresultsapp.ui.theme.blue
import com.example.gymresultsapp.ui.theme.purple
import kotlin.random.Random

@Entity
data class Exercise(
    val group: Group,
    val day: Int,
    val name: String,
    val sets: List<Set>,
    @PrimaryKey val id: Int? = null
)

enum class Group(val primary: Boolean, val day: Int, val string: String, val color : Color = (if (primary) blue else purple)) {
    CHEST(true, 1, "Chest"),
    BACK(true, 2, "Back"),
    LEGS(true, 3, "Legs"),
    BICEPS(false, 1, "Biceps"),
    TRICEPS(false, 2, "Triceps"),
    SHOULDERS(false, 3, "Shoulders");
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
) {
}
