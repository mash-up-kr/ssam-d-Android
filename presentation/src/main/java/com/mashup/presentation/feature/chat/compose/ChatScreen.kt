package com.mashup.presentation.feature.chat.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.presentation.R
import com.mashup.presentation.feature.chat.ChatViewModel
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/27
 */
@Composable
fun ChatRoute(
    onEmptyScreenButtonClick: () -> Unit,
    onChatClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    ChatScreen(
        modifier = modifier,
        onEmptyScreenButtonClick = onEmptyScreenButtonClick,
        onChatClick =  { id ->
            viewModel.getChats(id)
            onChatClick()
        }
    )
}

@Composable
fun ChatScreen(
    onEmptyScreenButtonClick: () -> Unit,
    onChatClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
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
            onEmptyScreenButtonClick = onEmptyScreenButtonClick,
            onChatClick = onChatClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun ChatContent(
    isConnected: Boolean,
    onEmptyScreenButtonClick: () -> Unit,
    onChatClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    if (!isConnected) {
        EmptyChatScreen(
            onButtonClick = onEmptyScreenButtonClick,
            modifier = modifier.fillMaxSize(),
        )
    } else {
        ChatListScreen(
            onMessageClick = onChatClick,
            modifier = modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatScreenPreview() {
    SsamDTheme(darkTheme = true) {
        ChatScreen(
            onEmptyScreenButtonClick = {},
            onChatClick = {}
        )
    }
}

