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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mashup.presentation.R
import com.mashup.presentation.feature.chat.ChatViewModel
import com.mashup.presentation.feature.chat.model.RoomUiModel
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/27
 */
@Composable
fun ChatRoute(
    onEmptyScreenButtonClick: () -> Unit,
    onChatClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val pagedChatRoomList = viewModel.pagingData.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.getChatRooms()
    }

    ChatScreen(
        modifier = modifier,
        onEmptyScreenButtonClick = onEmptyScreenButtonClick,
        onChatClick = { chatId ->
            onChatClick(chatId)
        },
        chatRoomList = pagedChatRoomList
    )
}

@Composable
fun ChatScreen(
    onEmptyScreenButtonClick: () -> Unit,
    onChatClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    chatRoomList: LazyPagingItems<RoomUiModel>
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
            onEmptyScreenButtonClick = onEmptyScreenButtonClick,
            onChatClick = onChatClick,
            modifier = Modifier.padding(paddingValues),
            chatRoomList = chatRoomList
        )
    }
}

@Composable
fun ChatContent(
    onEmptyScreenButtonClick: () -> Unit,
    onChatClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    chatRoomList: LazyPagingItems<RoomUiModel>
) {
    if (chatRoomList.itemCount == 0) {
        EmptyChatScreen(
            onButtonClick = onEmptyScreenButtonClick,
            modifier = modifier.fillMaxSize(),
        )
    } else {
        ChatRoomListScreen(
            onMessageClick = onChatClick,
            modifier = modifier
                .fillMaxSize()
                .padding(top = 8.dp),
            chatRoomList = chatRoomList
        )


    }
}

@Preview(showBackground = true)
@Composable
private fun ChatScreenPreview() {
    val viewModel: ChatViewModel = hiltViewModel()
    val pagedChatRoomList = viewModel.pagingData.collectAsLazyPagingItems()

    SsamDTheme(darkTheme = true) {
        ChatScreen(
            onEmptyScreenButtonClick = {},
            onChatClick = {},
            chatRoomList = pagedChatRoomList
        )
    }
}

