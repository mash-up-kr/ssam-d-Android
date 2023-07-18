package com.mashup.presentation.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.drawColoredShadow
import com.mashup.presentation.common.extension.isScrollingUp
import com.mashup.presentation.common.extension.pxToDp
import com.mashup.presentation.feature.home.model.SignalUiModel
import com.mashup.presentation.ui.common.KeyLinkLoading
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.theme.*

@Composable
fun HomeRoute(
    onKeywordContainerClick: () -> Unit,
    onGuideClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val pagedReceivedSignal = homeViewModel.pagingData.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        homeViewModel.getReceivedSignal()
    }

    HomeBackgroundScreen(
        pagedReceivedSignal = pagedReceivedSignal,
        onKeywordContainerClick = onKeywordContainerClick,
        onGuideClick = onGuideClick,
        onProfileMenuClick = onProfileMenuClick,
    )
}

@Composable
private fun HomeBackgroundScreen(
    pagedReceivedSignal: LazyPagingItems<SignalUiModel>,
    onKeywordContainerClick: () -> Unit,
    onGuideClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val signalCount = pagedReceivedSignal.itemCount

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_space),
            contentDescription = stringResource(R.string.login_description_space),
            contentScale = ContentScale.Crop
        )
        if (signalCount == 0) {
            Image(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.img_planet_home_empty),
                contentDescription = stringResource(R.string.home_planet_background_empty),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.img_planet_home_default),
                contentDescription = stringResource(R.string.home_planet_background_default),
                contentScale = ContentScale.Crop
            )
        }

        HomeScreen(
            signalCount = signalCount,
            pagedReceivedSignal = pagedReceivedSignal,
            onKeywordContainerClick = onKeywordContainerClick,
            onGuideClick = onGuideClick,
            onProfileMenuClick = onProfileMenuClick,
        )
    }
}


@Composable
fun HomeScreen(
    signalCount: Int,
    pagedReceivedSignal: LazyPagingItems<SignalUiModel>,
    onKeywordContainerClick: () -> Unit,
    onGuideClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberLazyListState()
    val isScrollingUp = scrollState.isScrollingUp()
    val isAtTop = !scrollState.canScrollBackward
    val topBarBackgroundColor by animateColorAsState(if (isAtTop) Color.Transparent else Gray01)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HomeScreenToolBar(
            topBarBackgroundColor = topBarBackgroundColor,
            onProfileMenuClick = onProfileMenuClick
        )
        HomeKeywordInfoContainer(
            onKeywordContainerClick = onKeywordContainerClick,
            visible = isScrollingUp,
            topBarBackgroundColor = topBarBackgroundColor
        )
        if (signalCount == 0) {
            EmptyContent(
                onGuideClick = onGuideClick
            )
        } else {
            when (pagedReceivedSignal.loadState.refresh) {
                LoadState.Loading -> KeyLinkLoading()
                is LoadState.Error -> { /* Error */ }
                else -> {
                    SignalCardList(
                        receivedSignals = pagedReceivedSignal,
                        scrollState = scrollState,
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeScreenToolBar(
    topBarBackgroundColor: Color,
    onProfileMenuClick: () -> Unit,
) {
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
            modifier = Modifier.clickable {
                onProfileMenuClick()
            },
            painter = painterResource(id = R.drawable.ic_profile_fill),
            contentDescription = stringResource(id = R.string.home_my_page_icon_content_description),
            contentScale = ContentScale.None
        )
    }
}

@Composable
private fun HomeKeywordInfoContainer(
    visible: Boolean,
    topBarBackgroundColor: Color,
    onKeywordContainerClick: () -> Unit
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
                .clickable { onKeywordContainerClick() }
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
private fun EmptyContent(
    onGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        EmptySignal(
            onGuideClick = onGuideClick
        )
    }
}

@Composable
private fun EmptySignal(
    onGuideClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.home_planet_guide, "Æ X-03"),
            style = Body1,
            color = White,
            textAlign = TextAlign.Center
        )
        KeyLinkRoundButton(
            text = stringResource(id = R.string.home_planet_guide_button),
            onClick = onGuideClick
        )
    }
}

@Composable
private fun SignalCardList(
    receivedSignals: LazyPagingItems<SignalUiModel>,
    scrollState: LazyListState,
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
            val item = receivedSignals[index]
            item?.let {
                SignalCard(signal = item)
            }
        }
    }
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
                text = signal.signalContent,
                style = Body1,
                color = White,
                maxLines = 3
            )
            SignalCardKeywordsChips(
                keywords = signal.keywords,
                keywordsCount = signal.keywordsCount
            )
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
            text = signal.senderName,
            style = Body2,
            color = White
        )
        Text(
            text = signal.receivedDisplayedTime,
            style = Caption2,
            color = Gray06
        )
    }
}

@Composable
private fun SignalCardKeywordsChips(
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
            SignalCardKeywordsChip(keyword = keywordChipItems[it])
        }
        item {
            SignalCardKeywordsChip(keyword = "+$keywordsCount")
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
//
//@Preview
//@Composable
//fun PreviewHomeScreen() {
//    HomeScreen(
//        signalCount = 0,
//        pagedReceivedSignal = emptyList(),
//        onKeywordContainerClick = {},
//        onGuideClick = {},
//        onProfileMenuClick = {},
//    )
//}