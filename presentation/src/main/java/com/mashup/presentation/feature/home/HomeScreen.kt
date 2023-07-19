package com.mashup.presentation.feature.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.isScrollingUp
import com.mashup.presentation.feature.home.model.SignalUiModel
import com.mashup.presentation.ui.common.KeyLinkLoading
import com.mashup.presentation.ui.theme.Gray01
import com.mashup.presentation.ui.theme.Heading4
import com.mashup.presentation.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun HomeRoute(
    onKeywordContainerClick: (List<String>) -> Unit,
    onGuideClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
    onReceivedSignalClick: () -> Unit,
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val pagedReceivedSignal = homeViewModel.receivedSignals.collectAsLazyPagingItems()
    val subscribeKeywordsUiState by homeViewModel.subscribeKeywordsState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        launch {
            homeViewModel.getReceivedSignal()
        }
        launch {
            homeViewModel.getSubscribedKeywords()
        }
    }

    HomeBackgroundScreen(
        subscribeKeywordsUiState = subscribeKeywordsUiState,
        pagedReceivedSignal = pagedReceivedSignal,
        onKeywordContainerClick = onKeywordContainerClick,
        onGuideClick = onGuideClick,
        onProfileMenuClick = onProfileMenuClick,
        onReceivedSignalClick = onReceivedSignalClick
    )
}

@Composable
private fun HomeBackgroundScreen(
    subscribeKeywordsUiState: SubscribeKeywordUiState,
    pagedReceivedSignal: LazyPagingItems<SignalUiModel>,
    onKeywordContainerClick: (List<String>) -> Unit,
    onGuideClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
    onReceivedSignalClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val signalCount = pagedReceivedSignal.itemCount

    Box(modifier = modifier.fillMaxSize()) {
        HomeBackgroundImage(signalCount = signalCount)
        HomeScreen(
            subscribeKeywordsUiState = subscribeKeywordsUiState,
            signalCount = signalCount,
            pagedReceivedSignal = pagedReceivedSignal,
            onKeywordContainerClick = onKeywordContainerClick,
            onGuideClick = onGuideClick,
            onProfileMenuClick = onProfileMenuClick,
            onReceivedSignalClick = onReceivedSignalClick
        )
    }
}

@Composable
fun BoxScope.HomeBackgroundImage(
    signalCount: Int,
    modifier: Modifier = Modifier
) {
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
}

@Composable
fun BoxScope.HomeScreen(
    subscribeKeywordsUiState: SubscribeKeywordUiState,
    signalCount: Int,
    pagedReceivedSignal: LazyPagingItems<SignalUiModel>,
    onKeywordContainerClick: (List<String>) -> Unit,
    onGuideClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
    onReceivedSignalClick: () -> Unit,
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
        if (subscribeKeywordsUiState is Success) {
            HomeKeywordInfoContainer(
                subscribeKeywords = subscribeKeywordsUiState.data,
                onKeywordContainerClick = onKeywordContainerClick,
                visible = isScrollingUp,
                topBarBackgroundColor = topBarBackgroundColor
            )
        }

        if (signalCount == 0) {
            EmptyContent(
                onGuideClick = onGuideClick
            )
        } else {
            when (pagedReceivedSignal.loadState.refresh) {
                LoadState.Loading -> KeyLinkLoading()
                is LoadState.Error -> { /* Error */ }
                else -> {
                    ReceivedSignalCards(
                        receivedSignals = pagedReceivedSignal,
                        scrollState = scrollState,
                        onReceivedSignalClick = onReceivedSignalClick,
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