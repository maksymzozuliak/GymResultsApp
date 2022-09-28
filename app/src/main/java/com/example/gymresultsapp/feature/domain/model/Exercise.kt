package com.example.gymresultsapp.feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    val group: Group,
    val name: String,
    val sets: List<Set>,
    @PrimaryKey val id: Int? = null
)

enum class Group {
    CHEST, BACK, LEGS, BICEPS, TRICEPS, SHOULDERS
}

data class Set(
    val num: Int,
    val reps: Int,
    val weight: Float
)