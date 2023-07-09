package com.mashup.presentation.detail.chat.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.detail.chat.model.ChatDetailUiModel
import com.mashup.presentation.ui.common.KeyLinkBottomSheetLayout
import com.mashup.presentation.ui.common.KeyLinkKeywordBottomSheet
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    onUpButtonClick: () -> Unit = {},
    onNavigateToMessageDetail: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val keywordBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = SwipeableDefaults.AnimationSpec,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )
    /**
     * val viewModel: ~ by hiltViewModel()
     * collectAsState~
     */
    KeyLinkBottomSheetLayout(
        bottomSheetContent = {
            KeyLinkKeywordBottomSheet(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(),
                matchedKeywords = ProvideChatDetailState.matchedKeywords
            )
        },
        modalSheetState = keywordBottomSheetState
    ) {
        Scaffold(
            modifier = modifier,
            backgroundColor = Black,
            topBar = {
                KeyLinkToolbar(
                    onClickBack = { onUpButtonClick() },
                    menuAction = {
                        IconButton(onClick = { /* */ }) {
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
                onChatItemClick = { onNavigateToMessageDetail() },
                keywordBottomSheetState = keywordBottomSheetState,
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
                    coroutineScope.launch {
                        if (modalSheetState.isVisible) modalSheetState.hide()
                        else modalSheetState.show()
                    }
                },
            matchedKeywords = chatDetailState.getMatchedKeywordSummery()
        )

        ChatContent(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            chat = chatDetailState.chat,
            onChatItemClick = { onChatItemClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChatDetailScreenPreview() {
    SsamDTheme {
        ChatDetailScreen()
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
            modalSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden),
            coroutineScope = rememberCoroutineScope()
        )
    }
}