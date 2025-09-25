package com.example.sleepnight.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sleepnight.data.persistence.dao.SleepNightDao
import com.example.sleepnight.data.persistence.entities.SleepNight

// Defining a database class (must be abstract , need to extends RoomDatabase and version)
@Database(entities = [SleepNight::class], version = 1)
// in the entities param need to specify all the data entities
abstract class AppDatabase : RoomDatabase() {
    abstract fun sleepNightDao(): SleepNightDao;
    // must be abstract with no arguments
}