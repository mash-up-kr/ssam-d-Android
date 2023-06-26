package com.mashup.presentation.chat.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/27
 */
@Composable
fun ChatScreen(
    navigateToSendSignal: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier,
        backgroundColor = Black,
        topBar = {
            Column {
                Text(
                    text = stringResource(R.string.toolbar_chat),
                    style = Heading3,
                    color = White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, top = 24.dp, bottom = 8.dp)
                )

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Gray03),
                    thickness = 1.dp
                )
            }
        }
    ) { paddingValues ->
        ChatContent(
            isConnected = false,
            navigateToSendSignal = { navigateToSendSignal() },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun ChatContent(
    isConnected: Boolean,
    navigateToSendSignal: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isConnected) {
        EmptyChatScreen(
            onButtonClick = { navigateToSendSignal() },
            modifier = modifier,
        )
    } else {
        ChatListScreen()
    }
}

@Composable
fun EmptyChatScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_chat_empty),
            contentDescription = stringResource(R.string.content_description_empty_chat)
        )

        Text(
            text = stringResource(id = R.string.chat_empty_first_description),
            style = Body1,
            color = White
        )

        Text(
            text = stringResource(id = R.string.chat_empty_second_description),
            style = Body1,
            color = White
        )

        KeyLinkRoundButton(
            text = stringResource(id = R.string.button_send_signal),
            onClick = { onButtonClick() },
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

@Composable
fun ChatListScreen() {

}

@Preview(showBackground = true)
@Composable
private fun ChatScreenPreview() {
    SsamDTheme(darkTheme = true) {
        ChatScreen()
    }
}

