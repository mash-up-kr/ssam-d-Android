package com.mashup.presentation.feature.signalzone.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.Gray08
import com.mashup.presentation.ui.theme.Heading4
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

@Composable
fun SignalZoneRoute(
    onToolbarClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    SignalZoneScreen(
        modifier = modifier,
        onToolbarClick = onToolbarClick
    )
}

@Composable
private fun SignalZoneScreen(
    onToolbarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
//        SignalZoneBackgroundImage()
        SignalZoneContentScreen(
            onToolbarClick = onToolbarClick
        )
    }
}

@Composable
private fun SignalZoneBackgroundImage() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.img_space),
        contentDescription = "",
    )
}

@Composable
private fun SignalZoneContentScreen(
    onToolbarClick: () -> Unit,
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
            modifier = Modifier
        )
    }
}

@Composable
fun CrashesContent(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
    ) {
        
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
            text = "시그널존",
            style = Heading4,
            color = White
        )
        Icon(
            modifier = Modifier
                .padding(start = 6.dp)
                .size(20.dp),
            painter = painterResource(id = R.drawable.ic_chat_help_24),
            tint = Gray08,
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalZoneScreenPreview() {
    SsamDTheme {
        SignalZoneScreen(
            onToolbarClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalZoneToolbarTitlePreview() {
    SsamDTheme {
        SignalZoneToolbar(
            onToolbarClick = {}
        )
    }
}
