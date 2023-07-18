package com.mashup.presentation.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.theme.Body1
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/18
 */

@Composable
fun EmptyContent(
    onGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        EmptySignal(
            onGuideClick = onGuideClick
        )
    }
}

@Composable
private fun EmptySignal(
    onGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.home_planet_guide, "Æ X-03"),
            style = Body1,
            color = White,
            textAlign = TextAlign.Center
        )
        KeyLinkRoundButton(
            text = stringResource(id = R.string.home_planet_guide_button),
            onClick = onGuideClick
        )
    }
}