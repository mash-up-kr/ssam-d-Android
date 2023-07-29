package com.mashup.data.source.remote.dto.responsebody.user

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.base.paging.PagedData
import com.mashup.domain.base.paging.Paging
import com.mashup.domain.model.signal.SentSignal
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SentSignalPagingResponse(
    val pageLength: Int,
    val totalPage: Int,
    @field:Json(name = "list")
    val signals: List<SentSignalResponse>,
) : DomainMapper<PagedData<List<SentSignal>>> {
    override fun toDomainModel(): PagedData<List<SentSignal>> {
        return PagedData(
            data = signals.map { it.toDomainModel() },
            paging = Paging(
                loadedSize = pageLength,
                totalPage = totalPage
            )
        )
    }
}

@JsonClass(generateAdapter = true)
data class SentSignalResponse(
    val signalId: Long,
    val content: String,
    val nickname: String,
    val sentTimeMillis: Long
) : DomainMapper<SentSignal> {
    override fun toDomainModel(): SentSignal {
        return SentSignal(
            signalId = signalId,
            content = content,
            nickname = nickname,
            sentTimeMillis = sentTimeMillis
        )
    }
}