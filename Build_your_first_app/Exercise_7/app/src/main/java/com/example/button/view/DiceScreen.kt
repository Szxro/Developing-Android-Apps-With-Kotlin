package com.example.button.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.button.R
import com.example.button.viewmodel.DiceViewModel

@Composable
fun DiceScreenRoot(viewModel: DiceViewModel, modifier: Modifier = Modifier): Unit{
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        DiceScreen(
            dice = viewModel.dice,
            rollDice = viewModel::rollDice, // passing the function as a lambda reference
            modifier);
    }
}

@Composable
private fun DiceScreen(
    dice: Int,
    rollDice: () -> Unit,
    modifier: Modifier = Modifier):Unit{
    Column(
        modifier,
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Image(
            painter = painterResource(dice),
            contentDescription = stringResource(id = R.string.roll)
        )
        Button(onClick = { rollDice() }) {
            Text(
                text = "Let's Roll"
            );
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonScreenPreview():Unit{
    val diceViewModel = DiceViewModel();

    DiceScreenRoot(diceViewModel);
}