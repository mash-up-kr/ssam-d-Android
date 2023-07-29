package com.mashup.domain.model.signal

import com.mashup.domain.base.DomainModel

data class SentSignal(
    val signalId: Long,
    val content: String,
    val nickname: String,
    val sentTimeMillis: Long
) : DomainModel

data class SentSignalDetail(
    val signalId: Long,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val nickname: String,
    val profileImage: String,
    val sentTimeMillis: Long
) : DomainModel