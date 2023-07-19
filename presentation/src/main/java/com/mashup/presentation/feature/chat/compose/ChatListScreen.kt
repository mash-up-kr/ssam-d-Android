package com.mashup.presentation.feature.chat.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mashup.presentation.R
import com.mashup.presentation.feature.chat.model.Message
import com.mashup.presentation.feature.chat.model.RoomUiModel
import com.mashup.presentation.ui.common.KeyLinkLoading
import com.mashup.presentation.ui.theme.*
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/27
 */

@Composable
fun ChatRoomListScreen(
    modifier: Modifier = Modifier,
    onMessageClick: (Long) -> Unit = {},
    chatRoomList: LazyPagingItems<RoomUiModel>
) {
    when (chatRoomList.loadState.refresh) {
        LoadState.Loading -> KeyLinkLoading()
        is LoadState.Error -> {/* Error */
        }
        else -> {
            ChatRoomList(
                modifier = modifier,
                onMessageClick = onMessageClick,
                chatRoomList = chatRoomList
            )
        }
    }
}

@Composable
fun ChatRoomList(
    modifier: Modifier = Modifier,
    onMessageClick: (Long) -> Unit = {},
    chatRoomList: LazyPagingItems<RoomUiModel>
) {
    LazyColumn(modifier = modifier) {
        // TODO: chatRoomList id값 unique한걸로 서버에 더미 요청 후 key값 추가
        items(
            count = chatRoomList.itemCount,
            contentType = chatRoomList.itemContentType { "ChatRoom" }
        ) { index ->
            chatRoomList[index]?.let {
                ChatRoomItem(
                    chatRoom = it,
                    onMessageClick = { onMessageClick(it.id) }
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ChatRoomItem(
    chatRoom: RoomUiModel,
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
        GlideImage(
            modifier = Modifier.size(36.dp),
            model = chatRoom.profileImage,
            contentDescription = stringResource(id = R.string.home_item_avatar_content_description),
            contentScale = ContentScale.Inside
        )

        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            ChatRoomInfo(
                date = chatRoom.receivedTime,
                userName = chatRoom.id.toString(), // FIXME
                matchedCount = chatRoom.matchingKeywordCount,
                modifier = Modifier.fillMaxWidth()
            )

            ChatRoomMessageInfo(
                recentMessage = chatRoom.recentSignalContent,
                isNewMessage = true, // FIXME
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp)
            )
        }

    }
}

@Composable
private fun ChatRoomInfo(
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
private fun ChatRoomMessageInfo(
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
    val chatRoomList = emptyList<RoomUiModel>()
    val flow = MutableStateFlow(PagingData.from(chatRoomList))
    val pagedChatRoomList = flow.collectAsLazyPagingItems()
    SsamDTheme {
        ChatRoomListScreen(chatRoomList = pagedChatRoomList)
    }
}
