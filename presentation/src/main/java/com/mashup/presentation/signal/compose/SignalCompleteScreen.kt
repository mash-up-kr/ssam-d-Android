package com.mashup.presentation.signal.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkMintText
import com.mashup.presentation.ui.theme.Body2
import com.mashup.presentation.ui.theme.Gray02
import com.mashup.presentation.ui.theme.Gray06
import com.mashup.presentation.ui.theme.SsamDTheme

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/24
 */
@Composable
fun SignalCompleteScreen(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
) {
    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lottie_signal_success)
    )
    val progress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever
    )

    BackHandler(true) {
        finishActivity()
    }

    Column(modifier = modifier.fillMaxSize()) {
        SignalCompleteContent(
            modifier = Modifier.weight(1f),
            lottieComposition = lottieComposition,
            progress = progress
        )

        KeyLinkButton(
            text = stringResource(id = R.string.close),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp, start = 20.dp, end = 20.dp),
            backgroundColor = Gray02,
            contentColor = Gray06,
            enable = true,
            onClick = { finishActivity() }
        )
    }
}

@Composable
private fun SignalCompleteContent(
    modifier: Modifier,
    lottieComposition: LottieComposition?,
    progress: Float
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        LottieAnimation(
            composition = lottieComposition,
            progress = { progress },
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )

        KeyLinkMintText(
            text = stringResource(R.string.signal_complete_title),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 82.dp, end = 82.dp)
        )

        Text(
            text = stringResource(R.string.signal_complete_subtitle),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 20.dp, end = 20.dp),
            style = Body2,
            color = Gray06,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignalCompleteScreenPreview() {
    SsamDTheme {
        SignalCompleteScreen()
    }
}