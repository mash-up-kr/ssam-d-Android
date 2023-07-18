package com.mashup.data.source.remote.dto.responsebody.chat

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.base.paging.PagedData
import com.mashup.domain.base.paging.Paging
import com.mashup.domain.model.chat.Room
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatRoomPagingResponse(
    val pageLength: Int,
    val totalPage: Int,
    val room: List<RoomResponse>,
): DomainMapper<PagedData<List<Room>>> {
    override fun toDomainModel(): PagedData<List<Room>> {
        return PagedData(
            data = room.map { it.toDomainModel() },
            paging = Paging(
                loadedSize = pageLength,
                totalPage = totalPage
            )
        )
    }
}

@JsonClass(generateAdapter = true)
data class RoomResponse(
    val id: Long,
    val keywords: List<String>,
    val recentSignalContent: String,
    val matchingKeywordCount: Int,
    val profileImage: String,
    val recentSignalReceivedTimeMillis: Long
) : DomainMapper<Room> {
    override fun toDomainModel(): Room {
        return Room(
            id = id,
            keywords = keywords,
            recentSignalContent = recentSignalContent,
            matchingKeywordCount = matchingKeywordCount,
            profileImage = profileImage,
            recentSignalReceivedTimeMillis = recentSignalReceivedTimeMillis
        )
    }
}