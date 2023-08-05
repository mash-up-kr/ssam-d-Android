package com.mashup.presentation.feature.signalzone.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mashup.presentation.BottomSheetType
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.drawColoredShadow
import com.mashup.presentation.common.extension.pxToDp
import com.mashup.presentation.ui.theme.*

@Composable
fun SignalZoneRoute(
    onShowBottomSheet: (BottomSheetType) -> Unit,
    onCrashClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {

    SignalZoneScreen(
        modifier = modifier,
        onShowBottomSheet = onShowBottomSheet,
        onCrashClick = onCrashClick
    )
}

@Composable
private fun SignalZoneScreen(
    onShowBottomSheet: (BottomSheetType) -> Unit,
    onCrashClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        SignalZoneBackgroundImage()
        SignalZoneContentScreen(
            onToolbarClick = { onShowBottomSheet(BottomSheetType.SIGNAL_ZONE) },
            onCrashClick = onCrashClick
        )
    }
}

@Composable
private fun BoxScope.SignalZoneBackgroundImage() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = R.drawable.img_signal_zone),
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun BoxScope.SignalZoneContentScreen(
    onToolbarClick: () -> Unit,
    onCrashClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        SignalZoneToolbar(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 12.dp, start = 20.dp, bottom = 8.dp, end = 20.dp),
            onToolbarClick = onToolbarClick
        )

        CrashesContent(
            onCardClick = onCrashClick,
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}

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

@Composable
private fun SignalZoneToolbar(
    onToolbarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable { onToolbarClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.toolbar_signal_zone),
            style = Heading4,
            color = White
        )
        Icon(
            modifier = Modifier
                .padding(start = 6.dp)
                .size(20.dp),
            painter = painterResource(id = R.drawable.ic_chat_help_24),
            tint = Gray08,
            contentDescription = stringResource(R.string.content_description_signal_zone)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalZoneScreenPreview() {
    SsamDTheme {
        SignalZoneScreen(
            onShowBottomSheet = {},
            onCrashClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalZoneToolbarTitlePreview() {
    SsamDTheme {
        SignalZoneToolbar(
            onToolbarClick = {}
        )
    }
}
