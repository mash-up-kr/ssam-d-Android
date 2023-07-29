package com.mashup.presentation.feature.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.findActivity
import com.mashup.presentation.common.extension.isScrollingUp
import com.mashup.presentation.feature.home.model.SignalUiModel
import com.mashup.presentation.ui.common.KeyLinkLoading
import com.mashup.presentation.ui.common.KeyLinkRoundIconButton
import com.mashup.presentation.ui.theme.Gray01
import com.mashup.presentation.ui.theme.Gray08
import com.mashup.presentation.ui.theme.Heading4
import com.mashup.presentation.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeRoute(
    onKeywordContainerClick: () -> Unit,
    onGuideClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
    onReceivedSignalClick: (Long) -> Unit,
    onSendSignalButtonClick: () -> Unit,
    homeViewModel: HomeViewModel,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier
) {

    val pagedReceivedSignal = homeViewModel.receivedSignals.collectAsLazyPagingItems()
    val subscribeKeywordsUiState by homeViewModel.subscribeKeywordsState.collectAsStateWithLifecycle()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            pagedReceivedSignal.refresh()
        })
    val context = LocalContext.current
    var backPressedTime = 0L

    LaunchedEffect(Unit) {
        launch {
            homeViewModel.getSubscribedKeywords()
        }
    }
    LaunchedEffect(pagedReceivedSignal.loadState) {
        if (pagedReceivedSignal.loadState.refresh is LoadState.NotLoading)
            isRefreshing = false
    }


    BackHandler(enabled = true) {
        if (System.currentTimeMillis() - backPressedTime <= 500L) {
            context.findActivity().finish()
        } else {
            onShowSnackbar(context.getString(R.string.app_finish_toast), SnackbarDuration.Short)
        }
        backPressedTime = System.currentTimeMillis()
    }

    Box(modifier = Modifier.pullRefresh(pullRefreshState), contentAlignment = Alignment.TopCenter) {
        HomeBackgroundScreen(
            subscribeKeywordsUiState = subscribeKeywordsUiState,
            pagedReceivedSignal = pagedReceivedSignal,
            onKeywordContainerClick = { keywords ->
                homeViewModel.setSubscribeKeywords(keywords)
                onKeywordContainerClick()
            },
            onGuideClick = onGuideClick,
            onProfileMenuClick = onProfileMenuClick,
            onReceivedSignalClick = onReceivedSignalClick,
            onSendSignalButtonClick = onSendSignalButtonClick
        )
        PullRefreshIndicator(refreshing = isRefreshing, state = pullRefreshState)
    }
    if (pagedReceivedSignal.loadState.append == LoadState.Loading) {
        KeyLinkLoading()
    }

}

@Composable
private fun HomeBackgroundScreen(
    subscribeKeywordsUiState: SubscribeKeywordUiState,
    pagedReceivedSignal: LazyPagingItems<SignalUiModel>,
    onKeywordContainerClick: (List<String>) -> Unit,
    onGuideClick: () -> Unit,
    onProfileMenuClick: () -> Unit,
    onReceivedSignalClick: (Long) -> Unit,
    onSendSignalButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val signalCount = pagedReceivedSignal.itemCount

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            KeyLinkRoundIconButton(
                text = stringResource(R.string.navigation_signal),
                onClick = onSendSignalButtonClick
            )
        }
    ) { paddingValues ->
        Box(modifier = modifier.fillMaxSize().padding(paddingValues)) {
            HomeBackgroundImage(signalCount = signalCount)
            HomeScreen(
                modifier = Modifier.padding(paddingValues),
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
    onReceivedSignalClick: (Long) -> Unit,
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
            onGuideClick = onGuideClick,
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
            ReceivedSignalCards(
                receivedSignals = pagedReceivedSignal,
                scrollState = scrollState,
                onReceivedSignalClick = onReceivedSignalClick,
            )
        }
    }
}

@Composable
private fun HomeScreenToolBar(
    topBarBackgroundColor: Color,
    onGuideClick: () -> Unit,
    onProfileMenuClick: () -> Unit
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
            modifier = Modifier.clickable { onGuideClick() },
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.home_my_planet),
                style = Heading4,
                color = White
            )
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_chat_help_24),
                contentDescription = "",
                tint = Gray08
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