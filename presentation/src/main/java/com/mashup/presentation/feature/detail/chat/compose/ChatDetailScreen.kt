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
import com.mashup.domain.exception.EmptyListException
import com.mashup.domain.exception.KeyLinkException
import com.mashup.presentation.R
import com.mashup.presentation.feature.detail.chat.model.ChatInfoUiModel
import com.mashup.presentation.common.extension.isScrollingUp
import com.mashup.presentation.feature.detail.chat.ChatDetailViewModel
import com.mashup.presentation.feature.detail.chat.model.ChatUiModel
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatDetailRoute(
    onBackClick: () -> Unit,
    onMessageClick: (Long, Long) -> Unit,
    onReportClick: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    viewModel: ChatDetailViewModel = hiltViewModel()
) {
    val chatInfoUiState by viewModel.chatInfoUiState.collectAsStateWithLifecycle()
    val showErrorMessage by viewModel.showSnackbarMessage.collectAsStateWithLifecycle(
        DisconnectRoomUiEvent.Idle
    )
    val pagedChatList = viewModel.chatPagingData.collectAsLazyPagingItems()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            pagedChatList.refresh()
        })

    LaunchedEffect(Unit) {
        launch { viewModel.getChatInfo() }
    }

    LaunchedEffect(pagedChatList.loadState) {
        if (pagedChatList.loadState.refresh is LoadState.NotLoading)
            isRefreshing = false

        if (pagedChatList.loadState.refresh is LoadState.Error) {
            val e = pagedChatList.loadState.refresh as LoadState.Error
            when (e.error) {
                is KeyLinkException -> e.error.message?.let {
                    onShowSnackbar(
                        it,
                        SnackbarDuration.Short
                    )
                }
            }
        }
    }

    LaunchedEffect(showErrorMessage) {
        when (showErrorMessage) {
            is DisconnectRoomUiEvent.Disconnect -> {
                onBackClick()
            }
            is DisconnectRoomUiEvent.Failure -> {
                onShowSnackbar(
                    (showErrorMessage as DisconnectRoomUiEvent.Failure).message.orEmpty(),
                    SnackbarDuration.Short
                )
            }
            else -> Unit
        }

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
                onMessageClick(viewModel.roomId, chatId)
            },
            onReportClick = onReportClick,
            onDisconnectRoom = { viewModel.disconnectRoom() },
            chatInfoUiState = chatInfoUiState,
            pagedChatList = pagedChatList
        )
        PullRefreshIndicator(refreshing = isRefreshing, state = pullRefreshState)
    }
    if (pagedChatList.loadState.append == LoadState.Loading) {
        KeyLinkLoading()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMessageClick: (Long) -> Unit,
    onReportClick: () -> Unit,
    onDisconnectRoom: () -> Unit,
    chatInfoUiState: ChatInfoUiState,
    pagedChatList: LazyPagingItems<ChatUiModel>
) {
    val scrollState = rememberLazyGridState()
    val isScrollingUp = scrollState.isScrollingUp()
    var showDisconnectDialog by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    var currentBottomSheetType by remember { mutableStateOf(BottomSheetType.CHAT_DETAIL_MORE) }

    val modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = SwipeableDefaults.AnimationSpec,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )

    val matchedKeywords = when (chatInfoUiState) {
        is ChatInfoUiState.Success -> chatInfoUiState.chatInfoUiModel.matchedKeywords
        else -> emptyList()
    }

    BackHandler(true) {
        coroutineScope.launch {
            if (modalBottomSheetState.isVisible) modalBottomSheetState.hide()
            else onBackClick()
        }
    }

    KeyLinkBottomSheetLayout(
        bottomSheetContent = {
            when (currentBottomSheetType) {
                BottomSheetType.CHAT_DETAIL_KEYWORD -> KeyLinkKeywordBottomSheet(
                    modifier = Modifier.fillMaxWidth(),
                    matchedKeywords = matchedKeywords
                )
                BottomSheetType.CHAT_DETAIL_MORE -> KeyLinkChatBottomSheet(
                    modifier = Modifier.fillMaxWidth(),
                    onDisconnectSignal = {
                        coroutineScope.launch {
                            modalBottomSheetState.hide()
                        }
                        showDisconnectDialog = true
                    },
                    onReportUser = {
                        coroutineScope.launch {
                            modalBottomSheetState.hide()
                            onReportClick()
                        }
                    }
                )
            }
        },
        modalSheetState = modalBottomSheetState
    ) {
        Scaffold(
            modifier = modifier,
            backgroundColor = Black,
            topBar = {
                KeyLinkToolbar(
                    onClickBack = { onBackClick() },
                    menuAction = {
                        IconButton(onClick = {
                            currentBottomSheetType = BottomSheetType.CHAT_DETAIL_MORE
                            coroutineScope.launch {
                                if (modalBottomSheetState.isVisible) modalBottomSheetState.hide()
                                else modalBottomSheetState.show()
                            }
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
                modalBottomSheetState = modalBottomSheetState,
                onChangeBottomSheetType = { currentBottomSheetType = it },
                isScrollingUp = isScrollingUp,
                scrollState = scrollState,
                coroutineScope = coroutineScope
            )
        }
    }

    if (showDisconnectDialog) {
        KeyLinkDisconnectSignalDialog(
            onDismissRequest = {},
            onDisconnectClick = {
                showDisconnectDialog = false
                onDisconnectRoom()
            },
            onCloseClick = { showDisconnectDialog = false }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChatDetailContent(
    modifier: Modifier = Modifier,
    chatInfoUiState: ChatInfoUiState,
    pagedChatList: LazyPagingItems<ChatUiModel>,
    onChatItemClick: (Long) -> Unit,
    modalBottomSheetState: ModalBottomSheetState,
    onChangeBottomSheetType: (BottomSheetType) -> Unit,
    isScrollingUp: Boolean,
    scrollState: LazyGridState,
    coroutineScope: CoroutineScope
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
                    modalBottomSheetState = modalBottomSheetState,
                    onChangeBottomSheetType = onChangeBottomSheetType,
                    isScrollingUp = isScrollingUp,
                    fromSignalZone = chatInfoUiState.chatInfoUiModel.fromSignalZone(),
                    coroutineScope = coroutineScope
                )
            }
            is ChatInfoUiState.Failure -> { /* failure */
            }
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChatInfoContent(
    modifier: Modifier = Modifier,
    chatInfoUiModel: ChatInfoUiModel,
    modalBottomSheetState: ModalBottomSheetState,
    onChangeBottomSheetType: (BottomSheetType) -> Unit,
    isScrollingUp: Boolean,
    fromSignalZone: Boolean,
    coroutineScope: CoroutineScope
) {
    Column {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
        ) {
            OtherUserInfo(
                modifier = Modifier.padding(bottom = 16.dp),
                othersNickName = chatInfoUiModel.othersNickName,
                othersProfileImage = chatInfoUiModel.othersProfileImage
            )
            if (!fromSignalZone) {
                MatchedKeywords(
                    modifier = modifier
                        .padding(bottom = 16.dp)
                        .clickable {
                            onChangeBottomSheetType(BottomSheetType.CHAT_DETAIL_KEYWORD)
                            coroutineScope.launch {
                                if (modalBottomSheetState.isVisible) modalBottomSheetState.hide()
                                else modalBottomSheetState.show()
                            }
                        },
                    matchedKeywords = chatInfoUiModel.getMatchedKeywordSummery(),
                    visible = isScrollingUp
                )
            }
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

enum class BottomSheetType {
    CHAT_DETAIL_KEYWORD, CHAT_DETAIL_MORE
}
