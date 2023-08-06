package com.mashup.domain.model

import com.mashup.domain.base.DomainModel

data class Crash(
    val id: Long,
    val content: String,
    val keywords: String,
    val userId: Long,
    val matchingKeywordCount: Int,
    val profileImage: String,
    val nickname: String,
    val receivedTimeMillis: Long
) : DomainModel
