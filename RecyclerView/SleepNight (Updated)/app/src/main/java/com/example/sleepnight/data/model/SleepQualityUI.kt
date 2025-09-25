package com.example.sleepnight.data.model

import androidx.annotation.DrawableRes

data class SleepQualityUI(
    val sleepDescription: String,
    @DrawableRes val sleepImageId: Int // Marking the item is going to return a drawable item id
)
