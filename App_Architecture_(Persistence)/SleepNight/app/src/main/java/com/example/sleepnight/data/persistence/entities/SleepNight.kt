package com.example.sleepnight.data.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Creating an entity
@Entity(tableName = "daily_sleep_quality_table")
data class SleepNight(
    @PrimaryKey(autoGenerate = true) // Defining the autogenerate PK
    val nightId: Long = 0L,

    @ColumnInfo(name = "startTimeMilliSeconds") // Defining columns
    val startTimeMilliSeconds: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "endTimeMilliSeconds")
    var endTimeMilliSeconds: Long = startTimeMilliSeconds,

    @ColumnInfo(name = "sleepQuality")
    var sleepQuality:Int = -1)