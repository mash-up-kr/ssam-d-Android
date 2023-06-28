package com.mashup.presentation.detail.chat.compose

import androidx.compose.ui.graphics.Color
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/29
 */

data class ChatDetailState(
    val othersProfileImage: String,
    val othersNickName: String,
    val matchedKeywords: List<String>,
    val chat: List<MessageState>
)

data class MessageState(
    val message: String,
    val userName: String,
    val date: String,
    val isMine: Boolean,
    val backgroundColor: MessageBackgroundColor? = null
)

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

val ProvideChatDetailState = ChatDetailState(
    othersProfileImage = "https://github-production-user-asset-6210df.s3.amazonaws.com/51078673/249568892-2120a1d4-58c3-4fbd-a8d7-f1e01571a0e6.png",
    othersNickName = "슈퍼니카",
    matchedKeywords = listOf("매쉬업", "일상", "디자인", "IT", "취준", "매쉬업", "일상", "디자인", "IT", "취준"),
    chat = listOf(
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.ORANGE
        ),
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.MINT
        ),
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.GREEN
        ),
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.PURPLE
        ),
        MessageState(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.PINK
        )
    )
)
