package com.example.sleepnight.data.datasource

import com.example.sleepnight.data.persistence.AppDatabase
import com.example.sleepnight.data.persistence.entities.SleepNight
import javax.inject.Inject

class SleepNightDataSource @Inject constructor(
    private val appDatabase: AppDatabase
) {
    suspend fun insertSleepNight(night: SleepNight): Unit{
        appDatabase.sleepNightDao().insert(night);
    }

    suspend fun updateSleepNight(night: SleepNight): Unit{
        appDatabase.sleepNightDao().update(night);
    }

    suspend fun deleteAll(): Unit{
        return appDatabase.sleepNightDao().clear();
    }

    suspend fun getNightById(nightId: Long): SleepNight?{
        return appDatabase.sleepNightDao().getNightById(nightId);
    }

    suspend fun getAllNights(): List<SleepNight>{
        return appDatabase.sleepNightDao().getAllNights();
    }

    suspend fun getTonightSleep(): SleepNight? {
        return appDatabase.sleepNightDao().getTonightSleep();
    }
}