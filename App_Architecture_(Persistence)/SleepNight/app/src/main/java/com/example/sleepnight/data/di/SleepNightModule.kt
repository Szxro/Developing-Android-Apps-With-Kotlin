package com.example.sleepnight.data.di

import android.content.Context
import androidx.room.Room
import com.example.sleepnight.data.persistence.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SleepNightModule {
    @Provides
    @Singleton
    fun provideDatabase(
        // Providing the application context by Hilt
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "daily_sleep_quality_table"
        ).fallbackToDestructiveMigration(true).build();
    }
}