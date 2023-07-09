package com.mashup.domain.model

data class ChatDetail(
    val id: Long,
    val keywords: List<String>,
    val matchingUserName: String,
    val matchingUserProfileImage: String,
    val chatColor: String,
    val chat: Chat
)

data class Chat(
    val id: Long,
    val content: String,
    val senderName: String,
    val createdAt: Long
)
