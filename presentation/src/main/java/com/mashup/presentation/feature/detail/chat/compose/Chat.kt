package com.mashup.presentation.feature.detail.chat.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mashup.presentation.feature.detail.chat.model.MessageBackgroundColor
import com.mashup.presentation.feature.detail.chat.model.ChatUiModel
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
@Composable
fun ChatContent(
    chatList: LazyPagingItems<ChatUiModel>,
    onChatItemClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    scrollState: LazyGridState
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = scrollState,
        contentPadding = PaddingValues(top = 8.dp, bottom = 42.dp, start = 20.dp, end = 20.dp)
    ) {
        items(
            count = chatList.itemCount,
            key = chatList.itemKey(ChatUiModel::id),
            contentType = chatList.itemContentType { "Chat" }
        ) { index ->
            val chat = chatList[index]
            chat?.let {
                MessageContent(
                    modifier = Modifier,
                    isMine = it.isMine,
                    message = it.message,
                    userName = it.userName,
                    date = it.date,
                    backgroundColor = it.backgroundColor,
                    onChatItemClick = { onChatItemClick(it.id) }
                )
            }
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
                    style = Body3,
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
                    style = Caption3,
                    color = if (isMine) Gray06 else White
                )
            }
        }
    }
}
