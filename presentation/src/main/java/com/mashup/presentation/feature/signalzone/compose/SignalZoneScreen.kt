package com.mashup.presentation.feature.signalzone.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mashup.domain.exception.EmptyListException
import com.mashup.domain.exception.KeyLinkException
import com.mashup.domain.model.Signal
import com.mashup.presentation.BottomSheetType
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.findActivity
import com.mashup.presentation.feature.signalzone.SignalZoneUiModel
import com.mashup.presentation.feature.signalzone.SignalZoneViewModel
import com.mashup.presentation.ui.common.KeyLinkLoading
import com.mashup.presentation.ui.theme.Gray08
import com.mashup.presentation.ui.theme.Heading4
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignalZoneRoute(
    onShowBottomSheet: (BottomSheetType) -> Unit,
    onCrashClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    viewModel: SignalZoneViewModel = hiltViewModel()
) {

    val pagedCrashes = viewModel.crashes.collectAsLazyPagingItems()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            pagedCrashes.refresh()
        })
    val context = LocalContext.current
    var backPressedTime = 0L
    var isCrashesEmpty by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        launch {
            pagedCrashes.refresh()
        }
    }

    LaunchedEffect(pagedCrashes.loadState) {
        if (pagedCrashes.loadState.refresh is LoadState.NotLoading) {
            isRefreshing = false
            isCrashesEmpty = false
        }

        if (pagedCrashes.loadState.refresh is LoadState.Error) {
            val e = pagedCrashes.loadState.refresh as LoadState.Error
            when (e.error) {
                is KeyLinkException -> e.error.message?.let {
                    onShowSnackbar(
                        it,
                        SnackbarDuration.Short
                    )
                }
                is EmptyListException -> {
                    isCrashesEmpty = true
                }
            }
        }
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
        SignalZoneScreen(
            modifier = modifier,
            onShowBottomSheet = onShowBottomSheet,
            onCrashClick = onCrashClick,
            crashes = pagedCrashes
        )
        PullRefreshIndicator(refreshing = isRefreshing, state = pullRefreshState)
    }
    if (pagedCrashes.loadState.append == LoadState.Loading
        || pagedCrashes.loadState.refresh == LoadState.Loading
    ) {
        KeyLinkLoading()
    }
}

@Composable
private fun SignalZoneScreen(
    onShowBottomSheet: (BottomSheetType) -> Unit,
    onCrashClick: (Long) -> Unit,
    crashes: LazyPagingItems<SignalZoneUiModel>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        SignalZoneBackgroundImage()
        SignalZoneContentScreen(
            onToolbarClick = { onShowBottomSheet(BottomSheetType.SIGNAL_ZONE) },
            onCrashClick = onCrashClick,
            crashes = crashes
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
    crashes: LazyPagingItems<SignalZoneUiModel>,
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
            crashes = crashes,
            modifier = Modifier.padding(top = 12.dp)
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
//        SignalZoneScreen(
//            onShowBottomSheet = {},
//            onCrashClick = {},
//        )
    }
}
