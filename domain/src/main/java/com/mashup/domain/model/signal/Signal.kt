package com.mashup.domain.model.signal

import com.mashup.domain.base.DomainModel

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/16
 */
data class Signal(
    val signalId: Long,
    val receiverId: Long?,
    val senderId: Long?,
    val senderName: String,
    val senderImageUrl: String,
    val signalContent: String,
    val keywords: List<String>,
    val keywordsCount: Int,
    val receivedTimeMillis: Long,
) : DomainModel
