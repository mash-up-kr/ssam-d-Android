package com.mashup.presentation.feature.detail.chat.compose

import com.mashup.presentation.feature.detail.message.model.MessageDetailUiModel
import com.mashup.presentation.feature.detail.chat.model.ChatInfoUiModel

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/29
 */

sealed class ChatInfoUiState {
    object Loading : ChatInfoUiState()

    data class Success(
        val chatInfoUiModel: ChatInfoUiModel
    ) : ChatInfoUiState()

    data class Failure(
        val message: String?
    ) : ChatInfoUiState()
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

sealed class MessageReplyUiEvent {
    object Idle: MessageReplyUiEvent()
    object SaveSuccess: MessageReplyUiEvent()
    data class Failure(
        val message: String
    ): MessageReplyUiEvent()
}

sealed class DisconnectRoomUiEvent {
    object Idle: DisconnectRoomUiEvent()
    object Disconnect: DisconnectRoomUiEvent()
    data class Failure(
        val message: String
    ): DisconnectRoomUiEvent()
}