package com.mashup.presentation.chat.model

import androidx.compose.runtime.Stable

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/29
 */

@Stable
data class Message(
    val userName: String,
    val matchedCount: Int,
    val date: String,
    val recentMessage: String,
    val isNewMessage: Boolean
)