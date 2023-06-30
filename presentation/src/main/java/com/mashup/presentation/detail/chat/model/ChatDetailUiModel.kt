package com.mashup.presentation.detail.chat.model

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/30
 */
data class ChatDetailUiModel(
    val othersProfileImage: String,
    val othersNickName: String,
    val matchedKeywords: List<String>,
    val chat: List<MessageUiModel>
)