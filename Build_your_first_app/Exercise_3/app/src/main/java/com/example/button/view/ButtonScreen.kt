package com.example.button.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.button.R
import com.example.button.viewmodel.ButtonViewModel

@Composable
fun ButtonScreenRoot(viewModel: ButtonViewModel, modifier: Modifier = Modifier): Unit{
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        ButtonScreen(viewModel.getTextContent(),modifier);
    }
}

@Composable
private fun ButtonScreen(text: String, modifier: Modifier = Modifier):Unit{
    Column(
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Text(text, fontSize = 30.sp);
        Button(onClick = {}) {
            Text(
                text = "Let's Roll"
            );
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonScreenPreview():Unit{
    val buttonViewModel: ButtonViewModel = ButtonViewModel();

    ButtonScreenRoot(buttonViewModel);
}