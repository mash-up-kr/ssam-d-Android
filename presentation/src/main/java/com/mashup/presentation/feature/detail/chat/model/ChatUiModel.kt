package com.mashup.presentation.feature.detail.chat.model

import androidx.compose.ui.graphics.Color
import com.mashup.domain.model.Chat
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/30
 */
data class ChatUiModel(
    val id: Long,
    val message: String,
    val userName: String,
    val date: String,
    val isMine: Boolean,
    val backgroundColor: MessageBackgroundColor? = null
) {
    companion object {
        fun Chat.toUiModel(): ChatUiModel {
            return ChatUiModel(
                id = id,
                message = content,
                userName = senderName,
                date = receivedTimeMillis.toString(),
                isMine = isMine,
                backgroundColor = getStringToEnumColor(chatColor)
            )
        }

        private fun getStringToEnumColor(color: String): MessageBackgroundColor? {
            MessageBackgroundColor.values().forEach {
                if (MessageBackgroundColor.valueOf(color) == it) return it
            }
            return null
        }
    }
}

enum class MessageBackgroundColor(
    private val startColor: Color,
    private val endColor: Color,
) {
    PURPLE(Purple01, Purple02),
    ORANGE(Brown, Orange),
    MINT(Green02, Green03),
    PINK(Pink01, Pink02),
    GREEN(Green01, GreenYellow);

    fun getGradientColors(): List<Color> {
        return listOf(startColor, endColor)
    }
}
