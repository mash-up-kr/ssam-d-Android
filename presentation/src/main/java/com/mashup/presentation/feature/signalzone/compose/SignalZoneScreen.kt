package com.mashup.presentation.feature.signalzone.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.BottomSheetType
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.Gray08
import com.mashup.presentation.ui.theme.Heading4
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

@Composable
fun SignalZoneRoute(
    onShowBottomSheet: (BottomSheetType) -> Unit,
    onCrashClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    SignalZoneScreen(
        modifier = modifier,
        onShowBottomSheet = onShowBottomSheet,
        onCrashClick = onCrashClick
    )
}

@Composable
private fun SignalZoneScreen(
    onShowBottomSheet: (BottomSheetType) -> Unit,
    onCrashClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        SignalZoneBackgroundImage()
        SignalZoneContentScreen(
            onToolbarClick = { onShowBottomSheet(BottomSheetType.SIGNAL_ZONE) },
            onCrashClick = onCrashClick
        )
    }
}

@Composable
private fun BoxScope.SignalZoneBackgroundImage() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.img_signal_zone),
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun BoxScope.SignalZoneContentScreen(
    onToolbarClick: () -> Unit,
    onCrashClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        SignalZoneToolbar(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 12.dp, start = 20.dp, bottom = 8.dp, end = 20.dp),
            onToolbarClick = onToolbarClick
        )

        CrashesContent(
            onCardClick = onCrashClick,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

@Composable
private fun SignalZoneToolbar(
    onToolbarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable { onToolbarClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.toolbar_signal_zone),
            style = Heading4,
            color = White
        )
        Icon(
            modifier = Modifier
                .padding(start = 6.dp)
                .size(20.dp),
            painter = painterResource(id = R.drawable.ic_chat_help_24),
            tint = Gray08,
            contentDescription = stringResource(R.string.content_description_signal_zone)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalZoneScreenPreview() {
    SsamDTheme {
        SignalZoneScreen(
            onShowBottomSheet = {},
            onCrashClick = {}
        )
    }
}
