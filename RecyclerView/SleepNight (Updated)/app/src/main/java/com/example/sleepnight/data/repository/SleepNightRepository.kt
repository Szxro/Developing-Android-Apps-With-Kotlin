package com.example.sleepnight.data.repository

import com.example.sleepnight.data.datasource.SleepNightDataSource
import com.example.sleepnight.data.persistence.entities.SleepNight
import javax.inject.Inject

class SleepNightRepository @Inject constructor(
    private val sleepNightDataSource: SleepNightDataSource
) {
    suspend fun insertSleepNight(night: SleepNight): Unit{
        sleepNightDataSource.insertSleepNight(night);
    }

    suspend fun updateSleepNight(night: SleepNight): Unit{
        sleepNightDataSource.updateSleepNight(night);
    }

    suspend fun deleteAll(): Unit{
        sleepNightDataSource.deleteAll();
    }

    suspend fun getNightById(nightId: Long): SleepNight?{
        return sleepNightDataSource.getNightById(nightId);
    }

    suspend fun getAllNights(): List<SleepNight>{
        return sleepNightDataSource.getAllNights();
    }

    suspend fun getTonightSleep(): SleepNight? {
        return sleepNightDataSource.getTonightSleep();
    }
}