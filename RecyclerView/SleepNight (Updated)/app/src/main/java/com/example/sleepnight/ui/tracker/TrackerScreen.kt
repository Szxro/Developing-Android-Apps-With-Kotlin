package com.example.sleepnight.ui.tracker

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sleepnight.R;
import com.example.sleepnight.common.util.DateUtils
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // key change here
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = { onStartButton() }) {
                    Text(stringResource(R.string.button_start))
                }
                Button(onClick = { onStopButton() }) {
                    Text(text = stringResource(R.string.button_stop))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.detail),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            currentNight?.let { current ->
                Text(
                    text = "Start: ${DateUtils.millisToDate(current.startTimeMilliSeconds)}"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                for (night in nights) {
                    Text(
                        text = "Start: ${DateUtils.millisToDate(night.startTimeMilliSeconds)}"
                    )
                    Text(
                        text = "End: ${DateUtils.millisToDate(night.endTimeMilliSeconds)}"
                    )
                    Text(
                        text = "Quality: ${night.sleepQuality}"
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Button(
                    onClick = { onClearButton() }
                ) {
                    Text(text = stringResource(R.string.button_clear))
                }
            }
        }
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