package com.mashup.presentation.feature.detail.chat.compose

import com.mashup.presentation.feature.detail.chat.model.ChatDetailUiModel
import com.mashup.presentation.feature.detail.chat.model.MessageBackgroundColor
import com.mashup.presentation.feature.detail.chat.model.MessageUiModel

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/29
 */
/**
 * API 연결 시 UiState (Success / Failed / Loading ..) 추가해야 합니당
 * 임시로 만들어놓은 파일
 */
val ProvideChatDetailState = ChatDetailUiModel(
    othersProfileImage = "https://github-production-user-asset-6210df.s3.amazonaws.com/51078673/249568892-2120a1d4-58c3-4fbd-a8d7-f1e01571a0e6.png",
    othersNickName = "슈퍼니카",
    matchedKeywords = listOf("매쉬업", "일상", "디자인", "IT", "취준", "매쉬업", "일상", "디자인", "IT", "취준"),
    chat = listOf(
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.ORANGE
        ),
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.MINT
        ),
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.GREEN
        ),
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "나",
            date = "2023년 6월 28일",
            isMine = true,
            backgroundColor = null
        ),
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.PURPLE
        ),
        MessageUiModel(
            message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
            userName = "슈퍼니카",
            date = "2023년 6월 28일",
            isMine = false,
            backgroundColor = MessageBackgroundColor.PINK
        )
    )
)
