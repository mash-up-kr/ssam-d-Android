package com.mashup.presentation.feature.detail.chat.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.presentation.R
import com.mashup.presentation.feature.chat.ChatViewModel
import com.mashup.presentation.feature.detail.chat.model.ChatDetailUiModel
import com.mashup.presentation.ui.common.KeyLinkBottomSheetLayout
import com.mashup.presentation.ui.common.KeyLinkChatBottomSheet
import com.mashup.presentation.ui.common.KeyLinkKeywordBottomSheet
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
@Composable
fun ChatDetailRoute(
    onBackClick: () -> Unit,
    onMessageClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatViewModel = hiltViewModel()
) {

    ChatDetailScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onMessageClick = onMessageClick,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatDetailScreen(
    onBackClick: () -> Unit,
    onMessageClick: () -> Unit,
    modifier: Modifier = Modifier
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
    val isMatchedKeywordVisible by remember {
        derivedStateOf {
            mutableStateOf( scrollState.firstVisibleItemScrollOffset == 0 )
        }
    }
    /**
     * val viewModel: ~ by hiltViewModel()
     * collectAsState~
     */
    KeyLinkBottomSheetLayout(
        bottomSheetContent = {
            when (currentBottomSheetType) {
                BottomSheetType.KEYWORD -> KeyLinkKeywordBottomSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(),
                    matchedKeywords = ProvideChatDetailState.matchedKeywords
                )
                BottomSheetType.MORE -> KeyLinkChatBottomSheet(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(),
                    onDisconnectSignal = {
                        // TODO: 연결끊기 api
                    },
                    onReportUser = {
                        // TODO: 신고하기 페이지 연결
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
                chatDetailState = ProvideChatDetailState,
                onChatItemClick = { },
                keywordBottomSheetState = keywordBottomSheetState,
                onChangeBottomSheetType = { currentBottomSheetType = it },
                isMatchedKeywordVisible = isMatchedKeywordVisible.value,
                scrollState = scrollState,
                coroutineScope = coroutineScope
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatDetailContent(
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
            modifier = Modifier.fillMaxWidth(),
            othersNickName = chatDetailState.othersNickName,
            othersProfileImage = chatDetailState.othersProfileImage
        )

        MatchedKeywords(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
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

        ChatContent(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
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
        ChatDetailScreen(onBackClick = {}, onMessageClick = {})
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