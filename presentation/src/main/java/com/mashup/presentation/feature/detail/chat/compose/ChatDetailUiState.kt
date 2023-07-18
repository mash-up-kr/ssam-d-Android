package com.mashup.presentation.feature.detail.chat.compose

import com.mashup.presentation.feature.detail.chat.model.ChatDetailUiModel

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/29
 */

sealed class ChatDetailUiState {
    object Loading : ChatDetailUiState()

    data class Success(
        val chatDetailUiModel: ChatDetailUiModel
    ) : ChatDetailUiState()

    data class Failure(
        val message: String?
    ) : ChatDetailUiState()
}
