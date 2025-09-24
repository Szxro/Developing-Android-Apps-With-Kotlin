package com.example.sleepnight.ui.quality

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sleepnight.ui.theme.SleepNightTheme
import com.example.sleepnight.R;
import kotlinx.serialization.Serializable

@Serializable
object SleepQualityRoute

@Composable
fun SleepQualityScreen(
    onSelectQuality: (Int) -> Unit
):Unit{
    SleepQualityScreenContent(
        onQualityChange = { selected ->
            onSelectQuality(selected)
        }
    );
}

@Composable
private fun SleepQualityScreenContent(
    onQualityChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.detail_sleep)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // First row: qualities 0, 1, 2
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 0..2) {
                SleepQualityIcon(quality = i, onClick = onQualityChange)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Second row: qualities 3, 4, 5
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (i in 3..5) {
                SleepQualityIcon(quality = i, onClick = onQualityChange)
            }
        }
    }
}

@Composable
private fun SleepQualityIcon(quality: Int, onClick: (Int) -> Unit) {
    val imageRes = when (quality) {
        0 -> R.drawable.ic_sleep_0
        1 -> R.drawable.ic_sleep_1
        2 -> R.drawable.ic_sleep_2
        3 -> R.drawable.ic_sleep_3
        4 -> R.drawable.ic_sleep_4
        5 -> R.drawable.ic_sleep_5
        else -> R.drawable.ic_sleep_0
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Sleep quality $quality",
        modifier = Modifier
            .size(75.dp)
            .clickable { onClick(quality) }
    )
}

@Preview(showSystemUi = true)
@Composable
fun SleepQualityScreenPreview():Unit{
    SleepNightTheme {
        SleepQualityScreenContent(
            onQualityChange = {}
        );
    }
}

