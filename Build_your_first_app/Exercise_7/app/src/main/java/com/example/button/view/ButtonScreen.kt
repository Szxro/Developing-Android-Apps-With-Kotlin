package com.example.button.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
    var dice by remember { mutableIntStateOf(R.drawable.empty_dice) };
    Column(
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Image(
            painter = painterResource(dice),
            contentDescription = stringResource(id = R.string.roll)
        )
        Button(onClick = {
            dice = rollDice();
        }) {
            Text(
                text = "Let's Roll"
            );
        }
    }
}

private fun rollDice(): Int{
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

@Preview(showBackground = true)
@Composable
private fun ButtonScreenPreview():Unit{
    val buttonViewModel: ButtonViewModel = ButtonViewModel();

    ButtonScreenRoot(buttonViewModel);
}