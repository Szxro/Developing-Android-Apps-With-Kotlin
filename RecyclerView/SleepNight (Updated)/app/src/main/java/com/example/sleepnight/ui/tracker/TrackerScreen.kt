package com.example.sleepnight.ui.tracker

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.example.sleepnight.common.util.SleepQualityUtils
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
    onNavigateToSleepQualityDescription: (SleepNight) -> Unit,
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
        onClearButton = viewModel::onClearTracking,
        onNavigateToSleepQualityDescription = onNavigateToSleepQualityDescription
    );
}

@Composable
private fun TrackerScreenContent(
    currentNight: SleepNight?,
    nights: List<SleepNight>,
    onStartButton: () -> Unit,
    onStopButton: () -> Unit,
    onClearButton: () -> Unit,
    onNavigateToSleepQualityDescription: (SleepNight) -> Unit,
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

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                // Control how cells are formed into columns or rows
                columns = GridCells.Adaptive(minSize = 100.dp)
            ) {
                // Providing a stable key enables item state to be consistent across data-set changes
                items(nights, key = { night ->  night.nightId }) { night ->
                    TrackerScreenListContent(
                        night,
                        onNavigateToSleepQualityDescription = {
                            onNavigateToSleepQualityDescription(night)
                        }
                    )
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
    modifier: Modifier = Modifier,
    onNavigateToSleepQualityDescription: () -> Unit
):Unit{
    val (description, imageId) = SleepQualityUtils.getSleepQualityDescriptionAndImageId(night.sleepQuality);

    val context = LocalContext.current;

    Column(
        modifier = modifier.fillMaxWidth().clickable{
            onNavigateToSleepQualityDescription()
        },
    ){
        Image(
            painter = painterResource(imageId),
            contentDescription = null,
            modifier = modifier.size(75.dp)
        )

        Text(text = description)
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
            onStopButton = {},
            onNavigateToSleepQualityDescription = {}
        );
    }
}