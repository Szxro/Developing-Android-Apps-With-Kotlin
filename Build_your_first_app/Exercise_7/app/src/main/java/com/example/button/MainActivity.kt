package com.example.button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.button.ui.theme.ButtonTheme
import com.example.button.view.DiceScreenRoot
import com.example.button.viewmodel.DiceViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonTheme {
                val diceViewModel by viewModels<DiceViewModel>();

                DiceScreenRoot(diceViewModel);
            }
        }
    }
}