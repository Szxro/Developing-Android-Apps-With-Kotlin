package com.example.button.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.button.R
import com.example.button.viewmodel.ButtonViewModel
import kotlin.random.Random

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
    // Using the state to change the value of the variable when the composition happen
    var content by remember { mutableStateOf(text) };

    Column(
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Text(content, fontSize = 30.sp);
        Button(onClick = {
            content = rollDice();
        }) {
            Text(
                text = "Let's Roll"
            );
        }
    }
}

private fun rollDice():String{
    val randomInt: Int = Random.nextInt(6) + 1;

    return "$randomInt";
}

@Preview(showBackground = true)
@Composable
private fun ButtonScreenPreview():Unit{
    val buttonViewModel: ButtonViewModel = ButtonViewModel();

    ButtonScreenRoot(buttonViewModel);
}