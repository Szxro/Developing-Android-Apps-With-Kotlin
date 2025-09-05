package com.example.helloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.helloandroid.ui.theme.HelloAndroidTheme
import com.example.helloandroid.view.HelloScreenRoot
import com.example.helloandroid.viewmodel.HelloViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloAndroidTheme {
                val helloViewModel: HelloViewModel = HelloViewModel();

                HelloScreenRoot(helloViewModel);
            }
        }
    }
}