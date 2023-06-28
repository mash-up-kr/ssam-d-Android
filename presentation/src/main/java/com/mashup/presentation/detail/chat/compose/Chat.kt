package com.mashup.presentation.detail.chat.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
@Composable
fun Chat(
    chat: List<MessageState>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(chat) { message ->
            Message(
                isMine = message.isMine,
                message = message.message,
                userName = message.userName,
                date = message.date,
                modifier = Modifier,
                backgroundColor = message.backgroundColor
            )
        }
    }
}

@Composable
fun Message(
    isMine: Boolean,
    message: String,
    userName: String,
    date: String,
    backgroundColor: MessageBackgroundColor?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(156.dp)
            .aspectRatio(39f / 52f),
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


@Preview(showBackground = true, name = "기본")
@Composable
private fun MessagePreview() {
    val myMessage = MessageState(
        message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
        userName = "나",
        date = "2023년 6월 28일",
        isMine = true,
        backgroundColor = null
    )

    SsamDTheme {
        Message(
            isMine = myMessage.isMine,
            message = myMessage.message,
            userName = myMessage.userName,
            date = myMessage.date,
            backgroundColor = myMessage.backgroundColor
        )
    }
}

@Preview(showBackground = true, name = "민트색")
@Composable
private fun MessagePreview3() {
    val othersMessage = MessageState(
        message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
        userName = "슈퍼니카",
        date = "2023년 6월 28일",
        isMine = false,
        backgroundColor = MessageBackgroundColor.MINT
    )

    SsamDTheme {
        Message(
            isMine = othersMessage.isMine,
            message = othersMessage.message,
            userName = othersMessage.userName,
            date = othersMessage.date,
            backgroundColor = othersMessage.backgroundColor
        )
    }
}