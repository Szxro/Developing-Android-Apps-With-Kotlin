package com.example.button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.button.ui.theme.ButtonTheme
import com.example.button.view.ButtonScreenRoot
import com.example.button.viewmodel.ButtonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonTheme {
                val buttonViewModel: ButtonViewModel = ButtonViewModel();

                ButtonScreenRoot(buttonViewModel);
            }
        }
    }
}