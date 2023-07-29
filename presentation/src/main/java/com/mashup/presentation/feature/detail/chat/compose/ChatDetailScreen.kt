package com.mashup.presentation.feature.detail.chat.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mashup.presentation.BottomSheetType
import com.mashup.presentation.R
import com.mashup.presentation.feature.detail.chat.model.ChatInfoUiModel
import com.mashup.presentation.common.extension.isScrollingUp
import com.mashup.presentation.feature.detail.ChatDetailViewModel
import com.mashup.presentation.feature.detail.chat.model.ChatUiModel
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.*
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatDetailRoute(
    roomId: Long,
    onBackClick: () -> Unit,
    onMessageClick: (Long, Long) -> Unit,
    controlBottomSheet: (BottomSheetType, List<String>?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatDetailViewModel = hiltViewModel()
) {
    val chatInfoUiState by viewModel.chatInfoUiState.collectAsStateWithLifecycle()
    val pagedChatList = viewModel.chatPagingData.collectAsLazyPagingItems()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            pagedChatList.refresh()
        })

    LaunchedEffect(Unit) {
        launch { viewModel.getChatInfo(roomId) }
        launch { viewModel.getChats(roomId) }
    }
    LaunchedEffect(pagedChatList.loadState) {
        if (pagedChatList.loadState.refresh is LoadState.NotLoading)
            isRefreshing = false
    }

    BackHandler(true) {
        onBackClick()
    }

    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize(), contentAlignment = Alignment.TopCenter
    ) {
        ChatDetailScreen(
            modifier = modifier,
            onBackClick = onBackClick,
            onMessageClick = { chatId ->
                onMessageClick(roomId, chatId)
            },
            onDisconnectRoom = { viewModel.disconnectRoom(roomId) },
            controlBottomSheet = controlBottomSheet,
            chatInfoUiState = chatInfoUiState,
            pagedChatList = pagedChatList
        )
        PullRefreshIndicator(refreshing = isRefreshing, state = pullRefreshState)
    }
    if (pagedChatList.loadState.append == LoadState.Loading) {
        KeyLinkLoading()
    }
}

@Composable
private fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMessageClick: (Long) -> Unit,
    onDisconnectRoom: () -> Unit,
    controlBottomSheet: (BottomSheetType, List<String>?) -> Unit,
    chatInfoUiState: ChatInfoUiState,
    pagedChatList: LazyPagingItems<ChatUiModel>
) {
    val scrollState = rememberLazyGridState()
    val isScrollingUp = scrollState.isScrollingUp()
    var showDisconnectDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(
                onClickBack = { onBackClick() },
                menuAction = {
                    IconButton(onClick = {
                        controlBottomSheet(BottomSheetType.CHAT_DETAIL_MORE, emptyList())
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more_horizontal_24),
                            contentDescription = null,
                            tint = White
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        ChatDetailContent(
            modifier = Modifier.padding(paddingValues),
            chatInfoUiState = chatInfoUiState,
            pagedChatList = pagedChatList,
            onChatItemClick = onMessageClick,
            controlBottomSheet = controlBottomSheet,
            isMatchedKeywordVisible = isScrollingUp,
            scrollState = scrollState
        )
    }

    if (showDisconnectDialog) {
        KeyLinkDisconnectSignalDialog(
            onDismissRequest = {},
            onDisconnectClick = {
                onDisconnectRoom()
                onBackClick()
                showDisconnectDialog = false
            },
            onCloseClick = { showDisconnectDialog = false }
        )
    }
}

@Composable
private fun ChatDetailContent(
    modifier: Modifier = Modifier,
    chatInfoUiState: ChatInfoUiState,
    pagedChatList: LazyPagingItems<ChatUiModel>,
    onChatItemClick: (Long) -> Unit,
    controlBottomSheet: (BottomSheetType, List<String>?) -> Unit,
    isMatchedKeywordVisible: Boolean,
    scrollState: LazyGridState
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (chatInfoUiState) {
            is ChatInfoUiState.Loading -> {
                KeyLinkLoading()
            }
            is ChatInfoUiState.Success -> {
                ChatInfoContent(
                    chatInfoUiModel = chatInfoUiState.chatInfoUiModel,
                    controlBottomSheet = controlBottomSheet,
                    isMatchedKeywordVisible = isMatchedKeywordVisible
                )
            }
            is ChatInfoUiState.Failure -> { /* failure */ }
        }

        Divider(color = Gray01)

        ChatContent(
            modifier = Modifier,
            chatList = pagedChatList,
            onChatItemClick = onChatItemClick,
            scrollState = scrollState
        )
    }
}

@Composable
private fun ChatInfoContent(
    modifier: Modifier = Modifier,
    chatInfoUiModel: ChatInfoUiModel,
    controlBottomSheet: (BottomSheetType, List<String>?) -> Unit,
    isMatchedKeywordVisible: Boolean
) {
    Column {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp),
        ) {
            OtherUserInfo(
                modifier = Modifier.padding(bottom = 16.dp),
                othersNickName = chatInfoUiModel.othersNickName,
                othersProfileImage = chatInfoUiModel.othersProfileImage
            )
            MatchedKeywords(
                modifier = modifier
                    .clickable {
                        controlBottomSheet(BottomSheetType.CHAT_DETAIL_KEYWORD, chatInfoUiModel.matchedKeywords)
                    },
                matchedKeywords = chatInfoUiModel.getMatchedKeywordSummery(),
                visible = isMatchedKeywordVisible
            )

        }
        if (!chatInfoUiModel.isAlive) {
            Box(
                modifier = Modifier
                    .background(color = Gray01),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    text = stringResource(id = R.string.chat_room_disconnect_room_guide),
                    style = Title3,
                    color = White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
