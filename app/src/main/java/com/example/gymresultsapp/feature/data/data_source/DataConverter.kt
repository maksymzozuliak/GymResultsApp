package com.example.gymresultsapp.feature.data.data_source

import androidx.room.TypeConverter
import com.example.gymresultsapp.feature.domain.model.Set
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromFloatList(value: List<Float>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Float>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toFloatList(value: String): List<Float> {
        val gson = Gson()
        val type = object : TypeToken<List<Float>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromSetsList(value: List<Set>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Set>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toSetsList(value: String): List<Set> {
        val gson = Gson()
        val type = object : TypeToken<List<Set>>() {}.type
        return gson.fromJson(value, type)
    }
}