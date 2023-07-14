package com.mashup.presentation.feature.chat.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.feature.chat.model.Message
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/27
 */

private val messageList = List(100) {
    Message(
        userName = "슈퍼 니카1",
        matchedCount = 10,
        date = "5월 30일",
        recentMessage = "키링 MAU 1000만 가즈아-!!",
        isNewMessage = true
    )
}

@Composable
fun ChatListScreen(
    modifier: Modifier = Modifier,
    onMessageClick: () -> Unit = {}
) {
    LazyColumn(modifier = modifier) {
        items(messageList) { message ->
            ChatItem(
                message = message,
                onMessageClick = { onMessageClick() }
            )
        }
    }
}

@Composable
fun ChatItem(
    message: Message,
    modifier: Modifier = Modifier,
    onMessageClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable { onMessageClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        /* 이미지 대신 임시로 추가 */
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Red),
        )

        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            ChatItemMessageInfoContent(
                date = message.date,
                userName = message.userName,
                matchedCount = message.matchedCount,
                modifier = Modifier.fillMaxWidth()
            )

            ChatItemMessageContent(
                recentMessage = message.recentMessage,
                isNewMessage = message.isNewMessage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
            )
        }

    }
}

@Composable
private fun ChatItemMessageInfoContent(
    userName: String,
    matchedCount: Int,
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = userName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Body2,
            color = White,
            modifier = Modifier.wrapContentWidth()
        )

        SignalChip(
            matchedCount = matchedCount,
            modifier = Modifier.padding(start = 4.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = date,
            style = Caption3,
            color = Gray06,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun ChatItemMessageContent(
    recentMessage: String,
    isNewMessage: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = recentMessage,
            style = Caption2,
            color = Gray06,
            modifier = Modifier.weight(1f)
        )

        if (isNewMessage) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(
                        color = Mint,
                        shape = CircleShape
                    )
                    .padding(vertical = 5.dp)
            )
        }
    }
}

@Composable
fun SignalChip(
    matchedCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Gray01)
            .padding(horizontal = 7.dp, vertical = 3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_frequency_12),
            tint = Gray09,
            contentDescription = stringResource(R.string.content_description_chat_item_frequency)
        )

        Text(
            text = stringResource(R.string.signal_matched_count, matchedCount),
            style = Caption3.copy(fontWeight = FontWeight.Bold),
            color = Gray09,
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalChipPreview() {
    SsamDTheme {
        SignalChip(matchedCount = 10)
    }
}

@Preview
@Composable
private fun CardItemPreview() {
    SsamDTheme {
        ChatListScreen()
    }
}
