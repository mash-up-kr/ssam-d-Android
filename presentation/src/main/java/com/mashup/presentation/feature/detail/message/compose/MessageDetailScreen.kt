package com.mashup.presentation.feature.detail.message.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.getDisplayedDateWithDay
import com.mashup.presentation.feature.detail.ChatDetailViewModel
import com.mashup.presentation.feature.detail.chat.compose.MessageDetailUiState
import com.mashup.presentation.feature.detail.message.model.MessageDetailUiModel
import com.mashup.presentation.ui.common.KeyLinkLoading
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/03
 */
@Composable
fun MessageDetailRoute(
    onBackClick: () -> Unit,
    onReportMenuClick: () -> Unit,
    onReplyButtonClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    roomId: Long,
    chatId: Long,
    viewModel: ChatDetailViewModel = hiltViewModel()
) {
    val messageDetailUiState by viewModel.messageDetailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getMessageDetail(roomId = roomId, chatId = chatId)
    }

    MessageDetailScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onReportMenuClick = onReportMenuClick,
        onReplyButtonClick = {
            onReplyButtonClick(roomId)
        },
        messageDetailUiState = messageDetailUiState
    )
}

@Composable
fun MessageDetailScreen(
    onBackClick: () -> Unit,
    onReportMenuClick: () -> Unit,
    onReplyButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    messageDetailUiState: MessageDetailUiState
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(
                onClickBack = onBackClick,
                menuAction = {
                    Icon(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .clickable {
                                onReportMenuClick()
                            },
                        painter = painterResource(id = R.drawable.ic_declare_24),
                        tint = White,
                        contentDescription = stringResource(R.string.content_description_report)
                    )
                }

            )
        },
    ) { paddingValues ->
        val contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 16.dp,
            start = 20.dp,
            end = 20.dp
        )
        when (messageDetailUiState) {
            is MessageDetailUiState.Loading -> KeyLinkLoading()
            is MessageDetailUiState.Success -> {
                MessageDetailContent(
                    contentPadding = contentPadding,
                    messageDetail = messageDetailUiState.messageDetail,
                    onReplyButtonClick = onReplyButtonClick
                )
            }
            is MessageDetailUiState.Failure -> {}
        }
    }
}

@Composable
private fun MessageDetailContent(
    contentPadding: PaddingValues,
    messageDetail: MessageDetailUiModel,
    onReplyButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MessageDetailContainer(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            othersName = messageDetail.nickname,
            date = messageDetail.receivedTimeMillis.getDisplayedDateWithDay(),
            message = messageDetail.content,
            matchedKeywords = messageDetail.keywords,
            profileImage = messageDetail.profileImage
        )

        // 남이 보낸 메세지고, 메세지가 살아있는 경우만 답장 버튼을 노출합니다.
        if (!messageDetail.isMine && messageDetail.isAlive) {
            KeyLinkRoundButton(
                modifier = Modifier.padding(top = 48.dp, bottom = 42.dp),
                text = stringResource(R.string.button_send_reply),
                onClick = onReplyButtonClick
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MessageScreenPreview() {
    SsamDTheme {
        MessageDetailScreen(
            onBackClick = {},
            onReportMenuClick = {},
            onReplyButtonClick = {},
            messageDetailUiState = MessageDetailUiState.Loading,
        )
    }
}