package com.example.gymresultsapp.di

import android.app.Application
import androidx.room.Room
import com.example.gymresultsapp.feature.data.data_source.ExerciseDatabase
import com.example.gymresultsapp.feature.data.repository.ExerciseRepositoryImplementation
import com.example.gymresultsapp.feature.domain.repository.ExerciseRepository
import com.example.gymresultsapp.feature.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExerciseDatabase(app: Application): ExerciseDatabase{
        return Room.databaseBuilder(
            app,
            ExerciseDatabase::class.java,
            ExerciseDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(db : ExerciseDatabase): ExerciseRepository {
        return ExerciseRepositoryImplementation(db.exerciseDao)
    }

    @Provides
    @Singleton
    fun provideExerciseUseCases(repository: ExerciseRepository): UseCases {
        return UseCases(
            getDayExercises = GetDayExercises(repository),
            deleteExercise = DeleteExercise(repository),
            addExercise = AddExercise(repository),
            getExercise = GetExercise(repository)
        )
    }
}