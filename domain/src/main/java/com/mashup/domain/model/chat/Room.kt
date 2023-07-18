package com.mashup.domain.model.chat

import com.mashup.domain.base.DomainModel

data class Room (
    val id: Long,
    val keywords: List<String>,
    val recentSignalContent: String,
    val matchingKeywordCount: Int,
    val profileImage: String,
    val recentSignalReceivedTimeMillis: Long
): DomainModel