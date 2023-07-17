package com.mashup.domain.model.chat

import com.mashup.domain.base.DomainModel

data class ChatDetail(
    val id: Long,
    val profileImage: String,
    val nickname: String,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val receivedTimeMillis: Long
) : DomainModel