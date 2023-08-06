package com.mashup.presentation.feature.signalzone.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.drawColoredShadow
import com.mashup.presentation.common.extension.pxToDp
import com.mashup.presentation.feature.signalzone.SignalZoneUiModel
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/05
 */

@Composable
fun CrashesContent(
    onCardClick: (Long) -> Unit,
    crashes: LazyPagingItems<SignalZoneUiModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
    ) {
        /* 페이징 적용 필요 */
        items(
            count = crashes.itemCount,
            key = crashes.itemKey(SignalZoneUiModel::crashId),
            contentType = crashes.itemContentType { "SignalZone Crashe" }
        ) { index ->
            crashes[index]?.let { crash ->
                CrashCard(
                    crash = crash,
                    onCardClick = onCardClick
                )
            }
        }
    }
}


@Composable
fun CrashCard(
    onCardClick: (Long) -> Unit,
    crash: SignalZoneUiModel,
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
            .clickable { onCardClick(crash.crashId) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp),
        ) {
            SenderInfo(crash)
            Text(
                text = crash.content,
                style = Body1,
                color = White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun SenderInfo(
    crash: SignalZoneUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            modifier = Modifier.size(24.dp),
            model = crash.profileImage,
            contentDescription = stringResource(id = R.string.home_item_avatar_content_description),
            contentScale = ContentScale.Inside
        )
        Text(
            text = crash.nickname,
            style = Body2,
            color = White
        )
        Text(
            text = crash.receivedDisplayedTime,
            style = Caption2,
            color = Gray06
        )
    }
}