package com.mashup.presentation.feature.detail.chat.compose

import androidx.paging.PagingData
import com.mashup.presentation.feature.detail.chat.model.ChatDetailUiModel
import com.mashup.presentation.feature.detail.message.model.MessageDetailUiModel

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/29
 */

sealed class ChatDetailUiState {
    object Loading : ChatDetailUiState()

    data class Success(
        val chatDetailUiModel: PagingData<ChatDetailUiModel>
    ) : ChatDetailUiState()

    data class Failure(
        val message: String?
    ) : ChatDetailUiState()
}

sealed class MessageDetailUiState {
    object Loading: MessageDetailUiState()
    data class Success(
        val messageDetail: MessageDetailUiModel
    ): MessageDetailUiState()

    data class Failure(
        val message: String?
    ): MessageDetailUiState()
}

sealed class MessageReplyUiState {
    object Idle: MessageReplyUiState()
    object Loading: MessageReplyUiState()
    object SaveSuccess: MessageReplyUiState()

    data class Failure(
        val message: String?
    ): MessageReplyUiState()
}