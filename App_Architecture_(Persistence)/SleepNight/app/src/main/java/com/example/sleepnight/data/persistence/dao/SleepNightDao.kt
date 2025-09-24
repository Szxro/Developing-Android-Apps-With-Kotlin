package com.example.sleepnight.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.sleepnight.data.persistence.entities.SleepNight

@Dao
interface SleepNightDao {

    @Insert
    suspend fun insert(night: SleepNight);

    @Update
    suspend fun update(night: SleepNight);

    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :nightId")
    suspend fun getNightById(nightId: Long): SleepNight?;

    @Query("SELECT * FROM daily_sleep_quality_table " +
            "ORDER BY nightId DESC")
    suspend fun getAllNights(): List<SleepNight>;

    @Query("DELETE FROM daily_sleep_quality_table")
    suspend fun clear();

    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    suspend fun getTonightSleep(): SleepNight?
}