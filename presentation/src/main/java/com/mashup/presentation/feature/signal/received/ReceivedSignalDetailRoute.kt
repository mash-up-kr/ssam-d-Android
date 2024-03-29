package com.mashup.presentation.feature.signal.received

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
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
import com.mashup.presentation.feature.detail.chat.compose.MessageDetailUiState
import com.mashup.presentation.feature.detail.message.compose.MatchedKeywordContainer
import com.mashup.presentation.feature.detail.message.compose.MessageDetailContainer
import com.mashup.presentation.feature.detail.message.compose.MessageDetailScreen
import com.mashup.presentation.feature.signal.received.model.ReceivedSignalDetailUiModel
import com.mashup.presentation.ui.common.KeyLinkLoading
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/22
 */
@Composable
fun ReceivedSignalDetailRoute(
    signalId: Long,
    onBackClick: () -> Unit,
    onReportMenuClick: () -> Unit,
    onReplyButtonClick: (Long) -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReceivedSignalViewModel = hiltViewModel()
) {
    val detailUiState by viewModel.receivedSignalUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getReceivedSignalDetail(signalId)
    }

    ReceivedSignalDetailScreen(
        onBackClick = onBackClick,
        onReportMenuClick = onReportMenuClick,
        onReplyButtonClick = onReplyButtonClick,
        onShowSnackbar = onShowSnackbar,
        receivedSignalDetailUiState = detailUiState
    )
}


@Composable
fun ReceivedSignalDetailScreen(
    onBackClick: () -> Unit,
    onReportMenuClick: () -> Unit,
    onReplyButtonClick: (Long) -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    receivedSignalDetailUiState: ReceivedSignalDetailUiState
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
            start = 20.dp
        )
        when (receivedSignalDetailUiState) {
            is Loading -> KeyLinkLoading()
            is Success -> {
                ReceivedSignalDetailContent(
                    contentPadding = contentPadding,
                    receivedSignalDetail = receivedSignalDetailUiState.data,
                    onReplyButtonClick = onReplyButtonClick
                )
            }
            is Error -> onShowSnackbar(receivedSignalDetailUiState.message.orEmpty(), SnackbarDuration.Short)
        }
    }
}

@Composable
private fun ReceivedSignalDetailContent(
    contentPadding: PaddingValues,
    receivedSignalDetail: ReceivedSignalDetailUiModel,
    onReplyButtonClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MessageDetailContainer(
            modifier = Modifier.weight(1f).padding(end = 20.dp),
            othersName = receivedSignalDetail.nickname,
            date = receivedSignalDetail.receivedTimeMillis,
            message = receivedSignalDetail.content,
            profileImage = receivedSignalDetail.profileImageUrl
        )
        MatchedKeywordContainer(
            modifier = Modifier.padding(vertical = 12.dp),
            matchedKeywords = receivedSignalDetail.keywords
        )
        KeyLinkRoundButton(
            modifier = Modifier.padding(top = 16.dp, bottom = 40.dp, end = 20.dp).align(Alignment.CenterHorizontally),
            text = stringResource(R.string.button_send_reply),
            onClick = { onReplyButtonClick(receivedSignalDetail.signalId) }
        )
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