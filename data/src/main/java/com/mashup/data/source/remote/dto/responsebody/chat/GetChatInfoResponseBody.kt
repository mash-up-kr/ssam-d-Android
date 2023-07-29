package com.mashup.data.source.remote.dto.responsebody.chat

import com.mashup.domain.base.DomainMapper
import com.mashup.domain.model.ChatInfo
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetChatInfoResponseBody(
    val id: Long,
    val keywords: List<String>,
    val matchingUserName: String,
    val matchingUserProfileImage: String,
    val chatColor: String,
    val isAlive: Boolean
) : DomainMapper<ChatInfo> {
    override fun toDomainModel(): ChatInfo {
        return ChatInfo(
            id = id,
            keywords = keywords,
            matchingUserName = matchingUserName,
            matchingUserProfileImage = matchingUserProfileImage,
            chatColor = chatColor,
            isAlive = isAlive
        )
    }
}
