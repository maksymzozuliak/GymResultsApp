package com.example.gymresultsapp.feature.domain.model

data class Exercise(
    val group: Group,
    val name: String,
    val availableWeight: List<Float>,
    val sets: List<Set>
)

enum class Group {
    CHEST, BACK, LEGS, BICEPS, TRICEPS, SHOULDERS
}

data class Set(
    val num: Int,
    val reps: Int,
    val weight: Float
)