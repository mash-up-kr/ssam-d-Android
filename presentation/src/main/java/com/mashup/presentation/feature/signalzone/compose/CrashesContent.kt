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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.drawColoredShadow
import com.mashup.presentation.common.extension.pxToDp
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/05
 */

@Composable
fun CrashesContent(
    onCardClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
    ) {
        /* 페이징 적용 필요 */
        items(20) {
            CrashCard(onCardClick = onCardClick)
        }
    }
}


@Composable
fun CrashCard(
    onCardClick: (Long) -> Unit,
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
            .clickable { onCardClick(0 /* id */) }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp),
        ) {
            SenderInfo(/* UI Model */)
            Text(
                text = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
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

    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            modifier = Modifier.size(24.dp),
            model = "receivedSignal.senderImageUrl",  // model
            contentDescription = stringResource(id = R.string.home_item_avatar_content_description),
            contentScale = ContentScale.Inside
        )
        Text(
            text = "슈퍼니카",  // model
            style = Body2,
            color = White
        )
        Text(
            text = "지금",  // model
            style = Caption2,
            color = Gray06
        )
    }
}