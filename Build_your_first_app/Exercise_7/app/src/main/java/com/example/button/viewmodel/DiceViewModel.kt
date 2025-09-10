package com.example.button.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.button.R

class DiceViewModel: ViewModel() {
    var dice by mutableIntStateOf(R.drawable.empty_dice)
        private set;

    public fun rollDice(): Unit{
        dice = listOf<Int>(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5
        ).random();
    }
}