package com.mashup.presentation.feature.detail.chat.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.feature.detail.chat.model.MessageBackgroundColor
import com.mashup.presentation.feature.detail.chat.model.MessageUiModel
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
@Composable
fun ChatContent(
    chat: List<MessageUiModel>,
    onChatItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(chat) { message ->
            MessageContent(
                modifier = Modifier,
                isMine = message.isMine,
                message = message.message,
                userName = message.userName,
                date = message.date,
                backgroundColor = message.backgroundColor,
                onChatItemClick = { onChatItemClick() }
            )
        }
    }
}

/**
 * aspectRatio
 * XML의 ConstraintLayout에서 사용하던 layout_constraintDimensinoRatio와 유사한 Modifier
 * 지정된 비율에 따라 width와 height값을 표현할 수 있음
 */
@Composable
fun MessageContent(
    isMine: Boolean,
    message: String,
    userName: String,
    date: String,
    backgroundColor: MessageBackgroundColor?,
    modifier: Modifier = Modifier,
    onChatItemClick: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .aspectRatio(ratio = 39f / 52f)  // 디자인에 나와있는 비율 적용
            .clickable {
                onChatItemClick()
            },
        shape = RoundedCornerShape(20.dp),
        border = if (isMine) BorderStroke(width = 1.dp, color = Gray03) else null,
        elevation = 10.dp
    ) {
        Box(
            modifier = when (isMine) {
                true -> {
                    Modifier.background(
                        color = Gray01
                    )
                }
                false -> {
                    Modifier.background(
                        brush = Brush.linearGradient(
                            colors = backgroundColor?.getGradientColors() ?: listOf(Gray01, Gray01),
                            start = Offset(0f, Float.POSITIVE_INFINITY),
                            end = Offset(Float.POSITIVE_INFINITY, 0f)
                        )
                    )
                }
            }

        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    modifier = Modifier.weight(8f),
                    text = message,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        lineHeight = 20.8.sp
                    ),
                    color = White
                )

                Text(
                    modifier = Modifier.weight(1f),
                    text = userName,
                    style = Body2,
                    color = White
                )

                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 4.dp),
                    text = date,
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 18.sp
                    ),
                    color = if (isMine) Gray06 else White
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DefaultMessagePreview() {
    val myMessage = MessageUiModel(
        message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
        userName = "나",
        date = "2023년 6월 28일",
        isMine = true,
        backgroundColor = null
    )

    SsamDTheme {
        MessageContent(
            isMine = myMessage.isMine,
            message = myMessage.message,
            userName = myMessage.userName,
            date = myMessage.date,
            backgroundColor = myMessage.backgroundColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MintMessagePreview() {
    val othersMessage = MessageUiModel(
        message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
        userName = "슈퍼니카",
        date = "2023년 6월 28일",
        isMine = false,
        backgroundColor = MessageBackgroundColor.MINT
    )

    SsamDTheme {
        MessageContent(
            isMine = othersMessage.isMine,
            message = othersMessage.message,
            userName = othersMessage.userName,
            date = othersMessage.date,
            backgroundColor = othersMessage.backgroundColor
        )
    }
}