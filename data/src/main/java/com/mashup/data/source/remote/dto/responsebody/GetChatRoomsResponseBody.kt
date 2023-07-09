package com.mashup.data.source.remote.dto.responsebody

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetChatRoomsResponseBody(
    val id: Int,
    val keywords: List<String>,
    val recentSignalContent: String,
    val matchingKeywordCount: Int,
    val profileImage: String,
    val recentSignalMillis: Int
)
