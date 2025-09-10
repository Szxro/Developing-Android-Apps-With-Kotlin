package com.example.aboutme.view

import androidx.compose.foundation.Image
import androidx.compose.ui.text.font.Font;
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aboutme.R;
import com.example.aboutme.constants.Constants
import com.example.aboutme.viewmodel.AboutMeViewModel


@Composable
fun AboutMeScreen(
    viewModel: AboutMeViewModel,
    modifier: Modifier = Modifier
): Unit {

    AboutMeContent(
        nickName = viewModel.myName.nickName,
        onNameChange = viewModel::onNameChange,
        isNickNameVisible = viewModel.myName.isNickNameVisible,
        onNickNameVisibilityChange = viewModel::onNickNameChangeVisibility,
        modifier = modifier
    );
}

@Composable
private fun AboutMeContent(
    nickName: String,
    isNickNameVisible: Boolean,
    onNameChange: (String) -> Unit,
    onNickNameVisibilityChange: () -> Unit,
    modifier: Modifier = Modifier
): Unit {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = dimensionResource(R.dimen.padding),
                end = dimensionResource(R.dimen.padding)
            )
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.layout_margin)))
        Text(
            text = stringResource(R.string.name_text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.small_padding)
                ),
            style = Constants.NAMESTYLE
        );

        if (!isNickNameVisible) {
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.layout_margin)))
            TextField(
                value = nickName,
                // Finalization behavior
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                placeholder = { Text(stringResource(R.string.what_is_your_nick_name)) },
                onValueChange = onNameChange,
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.layout_margin)))

            Button(onClick = { onNickNameVisibilityChange() }) {
                Text(
                    stringResource(R.string.done_button_text),
                    fontFamily = FontFamily(Font(R.font.roboto))
                );
            }
        } else {
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = nickName, style = Constants.NAMESTYLE)
        }

        Spacer(modifier = Modifier.height(16.dp));
        Image(
            painter = painterResource(R.drawable.yellow_star),
            contentDescription = stringResource(R.string.yello_star),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.layout_margin)))

        Text(
            text = stringResource(R.string.sebastian_bio),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.small_padding)
                ),
            style = Constants.NAMESTYLE.copy(
                lineHeight = 30.sp
            )
        );
    }
}

@Preview(showBackground = true)
@Composable
fun AboutMeScreenPreview(): Unit {
    val aboutMeViewModel: AboutMeViewModel = viewModel<AboutMeViewModel>()

    AboutMeScreen(aboutMeViewModel);
}