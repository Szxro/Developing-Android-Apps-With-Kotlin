package com.example.sleepnight.ui.tracker

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sleepnight.R;
import com.example.sleepnight.common.util.DateUtils
import com.example.sleepnight.data.model.SleepQualityUI
import com.example.sleepnight.data.persistence.entities.SleepNight
import com.example.sleepnight.ui.theme.SleepNightTheme
import kotlinx.serialization.Serializable

@Serializable
object TrackerRoute;

@Composable
fun TrackerScreen(
    viewModel: TrackerViewModel = hiltViewModel(),
    satisfaction: Int,
    onNavigateToSleepQuality: () -> Unit,
): Unit {
    val context = LocalContext.current;

    val currentNight by viewModel.currentNight.collectAsStateWithLifecycle();

    val nights by viewModel.nights.collectAsStateWithLifecycle();

    // Runs a suspend function or some code block when its key(s) change.
    LaunchedEffect(satisfaction) {
        viewModel.onStopTracking(satisfaction);
    }

    TrackerScreenContent(
        currentNight,
        nights,
        onStartButton = viewModel::onStartTracking,
        onStopButton = {
            currentNight?.let {
                // currentNight is not null
                onNavigateToSleepQuality();
            } ?: run {
                // currentNight is null
                Toast.makeText(context,"Operation not started yet", Toast.LENGTH_SHORT).show();
            }
        },
        onClearButton = viewModel::onClearTracking
    );
}

@Composable
private fun TrackerScreenContent(
    currentNight: SleepNight?,
    nights: List<SleepNight>,
    onStartButton: () -> Unit,
    onStopButton: () -> Unit,
    onClearButton: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = onStartButton) {
                    Text(text = stringResource(R.string.button_start))
                }
                Button(onClick = onStopButton) {
                    Text(text = stringResource(R.string.button_stop))
                }
            }
            currentNight?.let { current ->
                Text(
                    text = "Start: ${DateUtils.millisToDate(current.startTimeMilliSeconds)}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            Text(
                text = stringResource(R.string.detail),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(nights) { night ->
                    TrackerScreenListContent(night);
                }
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Button(
                onClick = onClearButton,
            ) {
                Text(text = stringResource(R.string.button_clear))
            }
        }
    }
}

@Composable
private fun TrackerScreenListContent(
    night: SleepNight,
    modifier: Modifier = Modifier
):Unit{
    val (description, imageId) = getSleepQualityDescriptionAndImageId(night.sleepQuality);

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ){
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = modifier.size(75.dp)
        )
      Column {
          Text(text = "Start: ${DateUtils.millisToDate(night.startTimeMilliSeconds)}")
          Text(text = "Quality: $description")
      }
        Spacer(modifier = modifier.height(12.dp))
    }
}

// Can implement simple logic into a screen , but more complex one is going to be in the view model
private fun getSleepQualityDescriptionAndImageId(quality: Int): SleepQualityUI {
    return when (quality) {
        0 -> SleepQualityUI("Very Bad", R.drawable.ic_sleep_0)
        1 -> SleepQualityUI("Poor", R.drawable.ic_sleep_1)
        2 -> SleepQualityUI("So-so", R.drawable.ic_sleep_2)
        3 -> SleepQualityUI("Okay", R.drawable.ic_sleep_3)
        4 -> SleepQualityUI("Good", R.drawable.ic_sleep_4)
        5 -> SleepQualityUI("Excellent", R.drawable.ic_sleep_5)
        else -> SleepQualityUI("Unknown", R.drawable.ic_sleep_0)
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TrackerScreenPreview(): Unit {
    SleepNightTheme(darkTheme = true) {
        TrackerScreenContent(
            null,
            emptyList(),
            onClearButton = {},
            onStartButton = {},
            onStopButton = {}
        );
    }
}