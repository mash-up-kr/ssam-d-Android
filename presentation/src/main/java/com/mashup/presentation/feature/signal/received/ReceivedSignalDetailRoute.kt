package com.mashup.presentation.feature.signal.received

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.presentation.feature.detail.chat.compose.MessageDetailUiState
import com.mashup.presentation.feature.detail.message.compose.MessageDetailScreen

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
    onReplyButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    receivedSignalViewModel: ReceivedSignalViewModel = hiltViewModel()
) {


    MessageDetailScreen(
        onBackClick = onBackClick,
        onReportMenuClick = onReportMenuClick,
        onReplyButtonClick = onReplyButtonClick,
        messageDetailUiState = MessageDetailUiState.Loading
    )
}