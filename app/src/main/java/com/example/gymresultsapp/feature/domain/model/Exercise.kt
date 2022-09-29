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

enum class Group(val color : Color) {
    CHEST(Color.Black),
    BACK(Color.Black),
    LEGS(Color.Black),
    BICEPS(Color.Black),
    TRICEPS(Color.Black),
    SHOULDERS(Color.Black);
}

data class Set(
    val num: Int,
    val reps: Int,
    val weight: Float
)
