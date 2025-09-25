package com.example.sleepnight.common.util

import com.example.sleepnight.R
import com.example.sleepnight.data.model.SleepQualityUI

object SleepQualityUtils{
    fun getSleepQualityDescriptionAndImageId(quality: Int): SleepQualityUI {
        return when (quality) {
            0 -> SleepQualityUI("Very Bad", R.drawable.ic_sleep_0)
            1 -> SleepQualityUI("Poor", R.drawable.ic_sleep_1)
            2 -> SleepQualityUI("So-so", R.drawable.ic_sleep_2)
            3 -> SleepQualityUI("Okay", R.drawable.ic_sleep_3)
            4 -> SleepQualityUI("Good", R.drawable.ic_sleep_4)
            5 -> SleepQualityUI("Excellent", R.drawable.ic_sleep_5)
            else -> SleepQualityUI("Unknown", R.drawable.ic_sleep_0)
        }
    }
}