package com.mashup.presentation.feature.detail.chat.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mashup.presentation.R
import com.mashup.presentation.feature.detail.chat.model.ChatInfoUiModel
import com.mashup.presentation.common.extension.isScrollingUp
import com.mashup.presentation.feature.detail.ChatDetailViewModel
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

@Composable
fun ChatDetailRoute(
    roomId: Long,
    onBackClick: () -> Unit,
    onMessageClick: (Long, Long) -> Unit,
    onReportClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getChatInfo(roomId)
        viewModel.getChats(roomId)
    }

    val chatInfoUiState by viewModel.chatInfoUiState.collectAsStateWithLifecycle()
    val pagedChatList = viewModel.chatPagingData.collectAsLazyPagingItems()

    when (chatInfoUiState) {
        is ChatInfoUiState.Loading -> { KeyLinkLoading() }
        else -> {
            ChatDetailScreen(
                modifier = modifier,
                onBackClick = onBackClick,
                onMessageClick = { chatId ->
                    onMessageClick(roomId, chatId)
                },
                onReportClick = onReportClick,
                onDisconnectRoom = { viewModel.disconnectRoom(roomId) },
                chatInfoUiState = chatInfoUiState,
                pagedChatList = pagedChatList
            )
        }
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
    val coroutineScope = rememberCoroutineScope()
    var currentBottomSheetType by remember { mutableStateOf(BottomSheetType.MORE) }
    val keywordBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = SwipeableDefaults.AnimationSpec,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )
    val chatMoreMenuBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = SwipeableDefaults.AnimationSpec,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )
    val scrollState = rememberLazyGridState()
    val isScrollingUp = scrollState.isScrollingUp()
    var showDisconnectDialog by remember { mutableStateOf(false) }

    val matchedKeywords = when (chatInfoUiState) {
        is ChatInfoUiState.Success -> chatInfoUiState.chatInfoUiModel.matchedKeywords
        else -> emptyList()
    }

    KeyLinkBottomSheetLayout(
        bottomSheetContent = {
            when (currentBottomSheetType) {
                BottomSheetType.KEYWORD -> KeyLinkKeywordBottomSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(),
                    matchedKeywords = matchedKeywords
                )
                BottomSheetType.MORE -> KeyLinkChatBottomSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(),
                    onDisconnectSignal = {
                        coroutineScope.launch {
                            chatMoreMenuBottomSheetState.hide()
                        }
                        showDisconnectDialog = true
                     },
                    onReportUser = {
                        onReportClick()
                    }
                )
            }

        },
        modalSheetState = when (currentBottomSheetType) {
            BottomSheetType.KEYWORD -> keywordBottomSheetState
            BottomSheetType.MORE -> chatMoreMenuBottomSheetState
        }
    ) {
        Scaffold(
            modifier = modifier,
            backgroundColor = Black,
            topBar = {
                KeyLinkToolbar(
                    onClickBack = { onBackClick() },
                    menuAction = {
                        IconButton(onClick = {
                            currentBottomSheetType = BottomSheetType.MORE
                            coroutineScope.launch {
                                if (chatMoreMenuBottomSheetState.isVisible) chatMoreMenuBottomSheetState.hide()
                                else chatMoreMenuBottomSheetState.show()
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
                keywordBottomSheetState = keywordBottomSheetState,
                onChangeBottomSheetType = { currentBottomSheetType = it },
                isMatchedKeywordVisible = isScrollingUp,
                scrollState = scrollState,
                coroutineScope = coroutineScope
            )
        }
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChatDetailContent(
    modifier: Modifier = Modifier,
    chatInfoUiState: ChatInfoUiState,
    pagedChatList: LazyPagingItems<ChatUiModel>,
    onChatItemClick: (Long) -> Unit,
    keywordBottomSheetState: ModalBottomSheetState,
    onChangeBottomSheetType: (BottomSheetType) -> Unit,
    isMatchedKeywordVisible: Boolean,
    scrollState: LazyGridState,
    coroutineScope: CoroutineScope
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (chatInfoUiState) {
            is ChatInfoUiState.Loading -> { KeyLinkLoading() }
            is ChatInfoUiState.Success -> {
                ChatInfoContent(
                    chatInfoUiModel = chatInfoUiState.chatInfoUiModel,
                    keywordBottomSheetState = keywordBottomSheetState,
                    onChangeBottomSheetType = onChangeBottomSheetType,
                    isMatchedKeywordVisible = isMatchedKeywordVisible,
                    coroutineScope = coroutineScope
                )
            }
            is ChatInfoUiState.Failure -> { /* failure */ }
        }

        Divider(color = Gray01)

        when (pagedChatList.loadState.refresh) {
            LoadState.Loading -> KeyLinkLoading()
            is LoadState.Error -> { /* Error */ }
            else -> {
                ChatContent(
                    modifier = Modifier,
                    chatList = pagedChatList,
                    onChatItemClick = onChatItemClick,
                    scrollState = scrollState
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChatInfoContent(
    modifier: Modifier = Modifier,
    chatInfoUiModel: ChatInfoUiModel,
    keywordBottomSheetState: ModalBottomSheetState,
    onChangeBottomSheetType: (BottomSheetType) -> Unit,
    isMatchedKeywordVisible: Boolean,
    coroutineScope: CoroutineScope
) {
    OtherUserInfo(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 16.dp),
        othersNickName = chatInfoUiModel.othersNickName,
        othersProfileImage = chatInfoUiModel.othersProfileImage
    )
    MatchedKeywords(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
            .clickable {
                onChangeBottomSheetType(BottomSheetType.KEYWORD)
                coroutineScope.launch {
                    if (keywordBottomSheetState.isVisible) keywordBottomSheetState.hide()
                    else keywordBottomSheetState.show()
                }
            },
        matchedKeywords = chatInfoUiModel.getMatchedKeywordSummery(),
        visible = isMatchedKeywordVisible
    )
}

enum class BottomSheetType {
    KEYWORD, MORE
}
