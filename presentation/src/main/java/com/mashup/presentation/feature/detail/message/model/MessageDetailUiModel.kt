package com.mashup.presentation.feature.detail.message.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.mashup.domain.model.chat.MessageDetail
import java.text.SimpleDateFormat
import java.util.*

data class MessageDetailUiModel(
    val id: Long,
    val profileImage: String,
    val nickname: String,
    val keywords: List<String>,
    val matchingKeywordCount: Int,
    val content: String,
    val receivedTimeMillis: Long,
    val isAlive: Boolean,
    val isMine: Boolean
) {
    fun getDisplayedDate(): String {
        val formatter = SimpleDateFormat("yyyy년 M월 d일", Locale.KOREA)
        return formatter.format(Date(receivedTimeMillis))
    }

    companion object {
        fun fromDomainModel(domain: MessageDetail): MessageDetailUiModel {
            with(domain) {
                return MessageDetailUiModel(
                    id = id,
                    profileImage = profileImage,
                    nickname = nickname,
                    keywords = keywords,
                    matchingKeywordCount = matchingKeywordCount,
                    content = content,
                    receivedTimeMillis = receivedTimeMillis,
                    isAlive = isAlive,
                    isMine = isMine
                )
            }
        }
    }
}