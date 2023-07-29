package com.mashup.presentation.feature.chat.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mashup.presentation.R
import com.mashup.presentation.feature.chat.ChatViewModel
import com.mashup.presentation.feature.chat.model.RoomUiModel
import com.mashup.presentation.ui.common.KeyLinkLoading
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/27
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatRoute(
    onEmptyScreenButtonClick: () -> Unit,
    onChatRoomClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val pagedChatRoomList = viewModel.pagingData.collectAsLazyPagingItems()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            pagedChatRoomList.refresh()
        })

    LaunchedEffect(Unit) {
        viewModel.getChatRooms()
    }
    LaunchedEffect(pagedChatRoomList.loadState) {
        if (pagedChatRoomList.loadState.refresh is LoadState.NotLoading)
            isRefreshing = false
    }

    Box(modifier = Modifier.pullRefresh(pullRefreshState).fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        ChatScreen(
            modifier = modifier,
            onEmptyScreenButtonClick = onEmptyScreenButtonClick,
            onChatRoomClick = { chatId ->
                onChatRoomClick(chatId)
            },
            chatRoomList = pagedChatRoomList
        )
        PullRefreshIndicator(refreshing = isRefreshing, state = pullRefreshState)
    }
    if (pagedChatRoomList.loadState.append == LoadState.Loading) {
        KeyLinkLoading()
    }
}

@Composable
fun ChatScreen(
    onEmptyScreenButtonClick: () -> Unit,
    onChatRoomClick: (Long) -> Unit,
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
            onChatRoomClick = onChatRoomClick,
            modifier = Modifier.padding(paddingValues),
            chatRoomList = chatRoomList
        )
    }
}

@Composable
fun ChatContent(
    onEmptyScreenButtonClick: () -> Unit,
    onChatRoomClick: (Long) -> Unit,
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
            onChatRoomClick = onChatRoomClick,
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
            onChatRoomClick = {},
            chatRoomList = pagedChatRoomList
        )
    }
}

