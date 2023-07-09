package com.mashup.data.source.remote.dto.responsebody

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetChatsResponseBody(
    val id: Int,
    val keywords: List<String>,
    val matchingUserName: String,
    val matchingUserProfileImage: String,
    val chatColor: String,
    val chat: ChatResponseBody
)

@JsonClass(generateAdapter = true)
data class ChatResponseBody(
    val id: Int,
    val content: String,
    val senderName: String,
    val createdAt: Int
)
