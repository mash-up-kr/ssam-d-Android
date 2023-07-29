package com.mashup.domain.model.chat

import com.mashup.domain.base.DomainModel

data class MessageDetail(
    val id: Long,
    val profileImage: String,
    val nickname: String,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val receivedTimeMillis: Long,
    val isAlive: Boolean,
    val isMine: Boolean,
    val isReplyable: Boolean
) : DomainModel