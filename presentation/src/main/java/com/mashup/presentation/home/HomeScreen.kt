package com.mashup.presentation.home

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.drawColoredShadow
import com.mashup.presentation.common.extension.pxToDp
import com.mashup.presentation.home.model.SignalUiModel
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.theme.*
import java.util.concurrent.TimeUnit

@Composable
fun HomeScreen(
    navigateToSubscribeKeyword: () -> Unit = {},
    navigateToGuide: () -> Unit = {}
) {
    val signals = emptyList<SignalUiModel>()
    val scrollState = rememberLazyListState()
    val isScrollingUp = scrollState.isScrollingUp()
    val isAtTop = !scrollState.canScrollBackward
    val topBarBackgroundColor by animateColorAsState(if (isAtTop) Color.Transparent else Gray01)


    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_space),
            contentDescription = stringResource(R.string.login_description_space),
            contentScale = ContentScale.Crop
        )
        if (signals.isEmpty()) {
            Image(
                modifier = Modifier.align(Alignment.BottomCenter),
                painter = painterResource(R.drawable.img_planet_home_empty),
                contentDescription = stringResource(R.string.home_planet_background_empty)
            )
        } else {
            Image(
                modifier = Modifier.align(Alignment.BottomCenter),
                painter = painterResource(R.drawable.img_planet_home_default),
                contentDescription = stringResource(R.string.home_planet_background_default)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeScreenToolBar(topBarBackgroundColor)
            HomeKeywordInfoContainer(
                onClick = { navigateToSubscribeKeyword() },
                visible = isScrollingUp,
                topBarBackgroundColor = topBarBackgroundColor
            )
            if (signals.isEmpty()) {
                EmptyContent(navigateToGuide = { navigateToGuide() })
            } else {
                SignalCardList(signals, scrollState)
            }
        }
    }
}

@Composable
private fun HomeScreenToolBar(topBarBackgroundColor: Color) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(color = topBarBackgroundColor)
            .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.home_my_planet),
                style = Heading4,
                color = White
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_profile_fill),
            contentDescription = stringResource(id = R.string.home_my_page_icon_content_description),
            contentScale = ContentScale.None
        )
    }
}

@Composable
private fun HomeKeywordInfoContainer(
    onClick: () -> Unit,
    visible: Boolean,
    topBarBackgroundColor: Color
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = topBarBackgroundColor)
                .clickable { onClick() }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_tag_mint),
                    contentDescription = stringResource(id = R.string.home_signal_icon_content_description),
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Inside,
                )
                Text(
                    text = stringResource(id = R.string.home_subscribe_keywords, 4),
                    style = Body2,
                    color = White
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_chevron_right_24),
                    contentDescription = stringResource(id = R.string.home_signal_icon_content_description),
                    modifier = Modifier.size(16.dp),
                    contentScale = ContentScale.Inside,
                )
            }
        }
    }
}

@Composable
private fun EmptyContent(navigateToGuide: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        EmptySignal(navigateToGuide)
    }
}

@Composable
private fun EmptySignal(navigateToGuide: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.home_planet_guide, "Æ X-03"),
            style = Body1,
            color = White,
            textAlign = TextAlign.Center
        )
        KeyLinkRoundButton(text = stringResource(id = R.string.home_planet_guide_button)) {
            navigateToGuide()
        }
    }
}

@Composable
private fun SignalCardList(signals: List<SignalUiModel>, scrollState: LazyListState) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
        state = scrollState
    ) {
        items(signals) { signal ->
            SignalCard(signal)
        }
    }
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }

    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
private fun SignalCard(signal: SignalUiModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawColoredShadow(
                color = Color.Blue,
                offsetX = 4.pxToDp().dp,
                offsetY = 6.pxToDp().dp,
                borderRadius = 28.pxToDp().dp,
                shadowRadius = 20.pxToDp().dp
            )
            .background(shape = RoundedCornerShape(12.dp), color = Black.copy(alpha = 0.6f))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp),
        ) {
            SignalCardUserInfo(signal)
            Text(
                text = signal.summery,
                style = Body1,
                color = White,
                maxLines = 3
            )
            SignalCardKeywordsChips(signal.keywords)
        }
    }
}

@Composable
private fun SignalCardUserInfo(signal: SignalUiModel) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.img_avatar),
            contentDescription = stringResource(id = R.string.home_item_avatar_content_description),
            contentScale = ContentScale.Inside
        )
        Text(
            text = signal.nickname,
            style = Body2,
            color = White
        )
        Text(
            text = signal.getDisplayedTime(),
            style = Caption1,
            color = Gray06
        )
    }
}

@Composable
private fun SignalCardKeywordsChips(keywords: List<String>) {
    val maxKeywordCount = 3

    val keywordChipItems = mutableListOf<String>().apply {
        if (keywords.size > maxKeywordCount) {
            addAll(keywords.subList(0, maxKeywordCount))
            add(
                "+${keywords.size.minus(maxKeywordCount)}"
            )
        } else {
            addAll(keywords)
        }
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
    ) {
        items(keywordChipItems.size) {
            SignalCardKeywordsChip(keyword = keywordChipItems[it])
        }
    }
}

@Composable
private fun SignalCardKeywordsChip(keyword: String) {
    Box(
        modifier = Modifier
            .background(color = Gray01, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp),
            text = keyword,
            fontSize = 10.sp,
            color = Gray10,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}