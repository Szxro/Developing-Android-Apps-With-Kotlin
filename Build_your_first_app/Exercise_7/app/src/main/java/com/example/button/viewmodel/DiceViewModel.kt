package com.example.button.viewmodel

import androidx.lifecycle.ViewModel
import com.example.button.R
import com.example.button.model.Dice
import kotlin.random.Random

class DiceViewModel: ViewModel() {
    private val _dice: Dice = Dice();

    public fun getInitialDice():Int{
        return _dice.initialDice;
    }

    public fun getDice():Int{
        val randomInt: Int = Random.nextInt(6) + 1;

        return when(randomInt){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}