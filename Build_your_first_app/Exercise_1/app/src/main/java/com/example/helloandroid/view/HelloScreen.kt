package com.example.helloandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloandroid.viewmodel.HelloViewModel

@Composable
fun HelloScreenRoot(viewModel: HelloViewModel, modifier: Modifier = Modifier){
    Box(
        Modifier.fillMaxSize(), // Fill the whole screen
        contentAlignment = Alignment.Center // Align the items in the center
    ){
        _HelloScreen(viewModel.gretting(),modifier);
    }
}

@Composable
private fun _HelloScreen(text: String, modifier: Modifier = Modifier): Unit{
    Text(text, modifier);
}


@Preview(showBackground = true)
@Composable
fun HelloScreenPreview(): Unit{
    val helloViewModel: HelloViewModel = HelloViewModel();

    HelloScreenRoot(helloViewModel);
}