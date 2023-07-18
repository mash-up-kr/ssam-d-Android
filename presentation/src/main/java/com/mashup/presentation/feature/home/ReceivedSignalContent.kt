package com.mashup.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.drawColoredShadow
import com.mashup.presentation.common.extension.pxToDp
import com.mashup.presentation.feature.home.model.SignalUiModel
import com.mashup.presentation.ui.common.KeywordChip
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/18
 */
@Composable
fun ReceivedSignalCards(
    receivedSignals: LazyPagingItems<SignalUiModel>,
    scrollState: LazyListState,
    onReceivedSignalClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
        state = scrollState
    ) {
        items(
            count = receivedSignals.itemCount,
            key = receivedSignals.itemKey(),
            contentType = receivedSignals.itemContentType()
        ) { index ->
            val receivedSignal = receivedSignals[index]
            receivedSignal?.let {
                ReceivedSignalCard(
                    receivedSignal = receivedSignal,
                    onCardClick = onReceivedSignalClick,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun ReceivedSignalCard(
    receivedSignal: SignalUiModel,
    onCardClick: () -> Unit,  // 람다 파라미터 수정 필요
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .drawColoredShadow(
                color = Color.Blue,
                offsetX = 4.pxToDp().dp,
                offsetY = 6.pxToDp().dp,
                borderRadius = 28.pxToDp().dp,
                shadowRadius = 20.pxToDp().dp
            )
            .background(shape = RoundedCornerShape(12.dp), color = Black.copy(alpha = 0.6f))
            .clickable { onCardClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp),
        ) {
            SenderInfo(receivedSignal)
            Text(
                text = receivedSignal.signalContent,
                style = Body1,
                color = White,
                maxLines = 3
            )
            KeywordsChips(
                keywords = receivedSignal.keywords,
                keywordsCount = receivedSignal.keywordsCount
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun SenderInfo(receivedSignal: SignalUiModel) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            modifier = Modifier.size(24.dp),
            model = receivedSignal.senderImageUrl,
            contentDescription = stringResource(id = R.string.home_item_avatar_content_description),
            contentScale = ContentScale.Inside
        )
        Text(
            text = receivedSignal.senderName,
            style = Body2,
            color = White
        )
        Text(
            text = receivedSignal.receivedDisplayedTime,
            style = Caption2,
            color = Gray06
        )
    }
}

@Composable
private fun KeywordsChips(
    keywords: List<String>,
    keywordsCount: Int,
    modifier: Modifier = Modifier
) {
    val maxKeywordCount = 3
    val keywordChipItems = mutableListOf<String>().apply {
        if (keywords.size > maxKeywordCount) {
            addAll(keywords.subList(0, maxKeywordCount))
        } else {
            addAll(keywords)
        }
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
    ) {
        items(keywordChipItems.size) {
            KeywordChip(keyword = keywordChipItems[it])
        }
        item {
            KeywordChip(keyword = "+$keywordsCount")
        }
    }
}