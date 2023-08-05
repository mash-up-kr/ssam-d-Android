package com.mashup.data.source.remote.dto.responsebody.crash

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.base.paging.PagedData
import com.mashup.domain.base.paging.Paging
import com.mashup.domain.model.Crash
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CrashPagingResponse(
    val pageLength: Int,
    val totalPage: Int,
    @field:Json(name = "list")
    val crashes: List<CrashResponse>
) : DomainMapper<PagedData<List<Crash>>> {
    override fun toDomainModel(): PagedData<List<Crash>> {
        return PagedData(
            data = crashes.map { it.toDomainModel() },
            paging = Paging(
                loadedSize = pageLength,
                totalPage = totalPage
            )
        )
    }
}

@JsonClass(generateAdapter = true)
data class CrashResponse(
    val id: Long,
    val content: Long,
    val keywords: Long,
    val userId: Long,
    val matchingKeywordCount: Int,
    val profileImage: String,
    val nickname: String,
    val receivedTimeMillis: Long
) : DomainMapper<Crash> {
    override fun toDomainModel(): Crash {
        return Crash(
            id = id,
            content = content,
            keywords = keywords,
            userId = userId,
            matchingKeywordCount = matchingKeywordCount,
            profileImage = profileImage,
            nickname = nickname,
            receivedTimeMillis = receivedTimeMillis
        )
    }
}
