package com.mashup.presentation.feature.detail.chat.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.feature.detail.chat.model.ChatDetailUiModel
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
@Composable
fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    onUpButtonClick: () -> Unit = {},
    onNavigateToMessageDetail: () -> Unit = {}
) {
    /**
     * val viewModel: ~ by hiltViewModel()
     * collectAsState~
     */
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
        }
    ) { paddingValues ->
        ChatDetailContent(
            modifier = Modifier.padding(paddingValues),
            chatDetailState = ProvideChatDetailState,
            onChatItemClick = { onNavigateToMessageDetail() }
        )
    }
}

@Composable
fun ChatDetailContent(
    chatDetailState: ChatDetailUiModel,
    onChatItemClick: () -> Unit,
    modifier: Modifier = Modifier
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
                .padding(horizontal = 20.dp),
            matchedKeywords = chatDetailState.matchedKeywords
        )

        Spacer(modifier = Modifier.height(12.dp))

        ChatContent(
            modifier = Modifier.padding(horizontal = 20.dp),
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

@Preview(showBackground = true)
@Composable
private fun ChatDetailContentPreview() {
    SsamDTheme {
        ChatDetailContent(
            chatDetailState = ProvideChatDetailState,
            onChatItemClick = {}
        )
    }
}