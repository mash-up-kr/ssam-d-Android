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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.R
import com.mashup.presentation.feature.chat.ChatViewModel
import com.mashup.presentation.feature.detail.chat.model.ChatDetailUiModel
import com.mashup.presentation.common.extension.isScrollingUp
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */

@Composable
fun ChatDetailRoute(
    chatId: Long,
    onBackClick: () -> Unit,
    onMessageClick: () -> Unit,
    onReportClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        Timber.i("ChatDetailRoute chatId: $chatId")
        viewModel.getChats(chatId)
    }

    when (val state = viewModel.chatDetailUiState.collectAsStateWithLifecycle().value) {
        is ChatDetailUiState.Loading -> {}
        is ChatDetailUiState.Success -> {
            ChatDetailScreen(
                modifier = modifier,
                onBackClick = onBackClick,
                onMessageClick = onMessageClick,
                onReportClick = onReportClick,
                chatDetailUiModel = state.chatDetailUiModel
            )
        }
        is ChatDetailUiState.Failure -> {}
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onMessageClick: () -> Unit,
    onReportClick: () -> Unit,
    chatDetailUiModel: ChatDetailUiModel
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

    KeyLinkBottomSheetLayout(
        bottomSheetContent = {
            when (currentBottomSheetType) {
                BottomSheetType.KEYWORD -> KeyLinkKeywordBottomSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(),
                    matchedKeywords = chatDetailUiModel.matchedKeywords
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
                chatDetailState = chatDetailUiModel,
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
    chatDetailState: ChatDetailUiModel,
    onChatItemClick: () -> Unit,
    modifier: Modifier = Modifier,
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
        OtherUserInfo(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp),
            othersNickName = chatDetailState.othersNickName,
            othersProfileImage = chatDetailState.othersProfileImage
        )
        MatchedKeywords(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 16.dp)
                .clickable {
                    onChangeBottomSheetType(BottomSheetType.KEYWORD)
                    coroutineScope.launch {
                        if (keywordBottomSheetState.isVisible) keywordBottomSheetState.hide()
                        else keywordBottomSheetState.show()
                    }
                },
            matchedKeywords = chatDetailState.getMatchedKeywordSummery(),
            visible = isMatchedKeywordVisible
        )
        Divider(color = Gray01)
        ChatContent(
            modifier = Modifier,
            chat = chatDetailState.chat,
            onChatItemClick = { onChatItemClick() },
            scrollState = scrollState
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatDetailScreenPreview() {
    SsamDTheme {
        // ChatDetailScreen(onBackClick = {}, onMessageClick = {}, onReportClick = {})
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
private fun ChatDetailContentPreview() {
    SsamDTheme {
        ChatDetailContent(
            chatDetailState = ProvideChatDetailState,
            onChatItemClick = {},
            keywordBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
            coroutineScope = rememberCoroutineScope(),
            onChangeBottomSheetType = {},
            isMatchedKeywordVisible = true,
            scrollState = rememberLazyGridState()
        )
    }
}

enum class BottomSheetType {
    KEYWORD, MORE
}