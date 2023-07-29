package com.mashup.data.source.remote.dto.responsebody.user

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.model.signal.SentSignalDetail
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SentSignalDetailResponse(
    val signalId: Long,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val profileImage: String,
    val nickname: String,
    val sentTimeMillis: Long,
) : DomainMapper<SentSignalDetail> {
    override fun toDomainModel(): SentSignalDetail {
        return SentSignalDetail(
            signalId = signalId,
            keywords = keywords,
            matchingKeywordCount = matchingKeywordCount,
            content = content,
            profileImage = profileImage,
            nickname = nickname,
            sentTimeMillis = sentTimeMillis
        )
    }
}