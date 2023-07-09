package com.mashup.data.source.remote.dto.responsebody

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetChatsResponseBody(
    val id: Long,
    val keywords: List<String>,
    val matchingUserName: String,
    val matchingUserProfileImage: String,
    val chatColor: String,
    val chat: ChatResponseBody
)

@JsonClass(generateAdapter = true)
data class ChatResponseBody(
    val id: Long,
    val content: String,
    val senderName: String,
    val createdAt: Long
)
