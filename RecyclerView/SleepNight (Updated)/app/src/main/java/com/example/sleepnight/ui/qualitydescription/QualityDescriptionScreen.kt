package com.example.sleepnight.ui.qualitydescription

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.location.LocationRequestCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sleepnight.common.util.DateUtils
import com.example.sleepnight.common.util.SleepQualityUtils
import com.example.sleepnight.data.persistence.entities.SleepNight
import com.example.sleepnight.ui.theme.SleepNightTheme
import kotlinx.serialization.Serializable
import com.example.sleepnight.R;
import com.example.sleepnight.ui.tracker.TrackerViewModel
import kotlin.math.log

@Serializable
data class QualityDescriptionRoute(
    val quality: Int,
    val start: Long,
    val end: Long,
)

@Composable
fun QualityDescriptionScreen(
    quality: Int,
    start: Long,
    end: Long,
    onCloseButton: () -> Unit,
):Unit{

    QualityDescriptionScreenContent(
        quality,
        start,
        end,
        onCloseButton
    );
}

@Composable
private fun QualityDescriptionScreenContent(
    quality: Int,
    start: Long,
    end: Long,
    onCloseButton: () -> Unit,
):Unit{
    val (description, resId) = SleepQualityUtils.getSleepQualityDescriptionAndImageId(quality);

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(resId),
            contentDescription = null,
            modifier = Modifier.size(75.dp)
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Column{
                Text("Quality: $description");
                Text(text = "Start: ${DateUtils.millisToDate(start)}");
                Text(text = "End: ${DateUtils.millisToDate(end)}");
            }
        }
        Button(
            onClick = onCloseButton,
        ) {
            Text(stringResource(R.string.button_close));
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun QualityDescriptionScreenPreview():Unit{
    SleepNightTheme {
        QualityDescriptionScreenContent(
            quality = 5,
            start = 0L,
            end = 0L,
            onCloseButton = { }
        );
    }
}