package com.mashup.domain.model

import com.mashup.domain.base.DomainModel

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/16
 */
data class ReceivedSignal(
    val signalId: Int,
    val receiverId: Int,
    val senderId: Int,
    val senderName: String,
    val senderImageUrl: String,
    val signalContent: String,
    val keywords: List<String>,
    val keywordsCount: Int,
    val receivedTimeMillis: Long,
) : DomainModel
