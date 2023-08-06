package com.mashup.presentation.feature.chat.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mashup.presentation.BottomSheetType
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
    onBackClick: () -> Unit,
    onEmptyScreenButtonClick: () -> Unit,
    onChatRoomClick: (Long) -> Unit,
    onShowBottomSheet: (BottomSheetType) -> Unit,
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
    var isChatRoomEmpty by remember { mutableStateOf(false) }

    LaunchedEffect(pagedChatRoomList.loadState) {
        if (pagedChatRoomList.loadState.refresh is LoadState.NotLoading) {
            isRefreshing = false
            isChatRoomEmpty = false
        }

        if (pagedChatRoomList.loadState.refresh is LoadState.Error) {
            isChatRoomEmpty = true
        }
    }

    BackHandler(true) {
        onBackClick()
    }

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        ChatScreen(
            modifier = modifier,
            onEmptyScreenButtonClick = onEmptyScreenButtonClick,
            onChatRoomClick = { chatId ->
                onChatRoomClick(chatId)
            },
            onShowBottomSheet = onShowBottomSheet,
            chatRoomList = pagedChatRoomList,
            isChatRoomEmpty = isChatRoomEmpty
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
    onShowBottomSheet: (BottomSheetType) -> Unit,
    modifier: Modifier = Modifier,
    chatRoomList: LazyPagingItems<RoomUiModel>,
    isChatRoomEmpty: Boolean
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            Column {
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 24.dp, bottom = 8.dp)
                        .clickable {
                            onShowBottomSheet(BottomSheetType.CHAT_CONNECTED)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.toolbar_chat),
                        style = Heading3,
                        color = White
                    )
                    Icon(
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .size(20.dp),
                        painter = painterResource(id = R.drawable.ic_chat_help_24),
                        contentDescription = "",
                        tint = Gray08
                    )
                }
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
            chatRoomList = chatRoomList,
            isChatRoomEmpty = isChatRoomEmpty
        )
    }
}

@Composable
fun ChatContent(
    onEmptyScreenButtonClick: () -> Unit,
    onChatRoomClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    chatRoomList: LazyPagingItems<RoomUiModel>,
    isChatRoomEmpty: Boolean
) {
    if (isChatRoomEmpty) {
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
