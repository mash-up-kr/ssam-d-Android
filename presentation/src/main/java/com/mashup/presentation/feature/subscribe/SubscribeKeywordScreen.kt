package com.mashup.presentation.feature.subscribe

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.R
import com.mashup.presentation.feature.home.HomeViewModel
import com.mashup.presentation.feature.home.Nothing
import com.mashup.presentation.feature.home.UpdateSuccess
import com.mashup.presentation.feature.home.UpdateFailed
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.*
import okhttp3.internal.toImmutableList

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/25
 */
@Composable
fun SubscribeRoute(
    onBackClick: () -> Unit,
    onSaveSuccess: () -> Unit,
    onSaveFailed: () -> Unit,
    modifier: Modifier = Modifier,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    homeViewModel: HomeViewModel
) {
    val subscribeKeywords = homeViewModel.subscribeKeywords.toImmutableList()
    val uiEvent by homeViewModel.eventFlow.collectAsStateWithLifecycle(initialValue = Nothing)

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            UpdateSuccess -> onSaveSuccess()
            is UpdateFailed -> onSaveFailed()
            else -> {}
        }
    }

    SubscribeKeywordScreen(
        subscribeKeywords = subscribeKeywords,
        modifier = modifier,
        onBackClick = onBackClick,
        onShowSnackbar = onShowSnackbar,
        onSaveButtonClick = homeViewModel::updateSubscribeKeywords,
        onAddKeyword = homeViewModel::addSubscribeKeywords,
        onDeleteKeyword = homeViewModel::deleteSubscribeKeywords,
    )
}

@Composable
fun SubscribeKeywordScreen(
    subscribeKeywords: List<String>,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onBackClick: () -> Unit,
    onSaveButtonClick: () -> Unit,
    onAddKeyword: (String) -> Unit,
    onDeleteKeyword: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val keywords = remember { mutableStateListOf<String>() }
    var showGoBackDialog by remember { mutableStateOf(false) }
    val snackbarMessage = stringResource(id = R.string.snackbar_subscribe_keyword)
    val focusManager = LocalFocusManager.current

    BackHandler(true) {
        showGoBackDialog = true
    }

    Column(modifier = modifier.fillMaxSize()) {
        KeyLinkToolbar(
            onClickBack = { showGoBackDialog = true }
        )
        SubscribeKeywordContent(
            modifier = Modifier
                .weight(1f)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            keywords = subscribeKeywords,
            onKeywordAdd = onAddKeyword,
            onKeywordDelete = onDeleteKeyword
        )
        KeyLinkButton(
            text = stringResource(R.string.button_save),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 20.dp),
            enable = subscribeKeywords.isNotEmpty(),
            onClick = {
                focusManager.clearFocus()
                onShowSnackbar(snackbarMessage, SnackbarDuration.Short)
                onSaveButtonClick()
            }
        )
    }
    if (showGoBackDialog) {
        KeyLinkGoBackDialog(
            onDismissRequest = {},
            onGoBackClick = {
                onBackClick()
                showGoBackDialog = false
            },
            onCloseClick = { showGoBackDialog = false }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SubscribeKeywordContent(
    keywords: List<String>,
    modifier: Modifier = Modifier,
    onKeywordAdd: (String) -> Unit,
    onKeywordDelete: (Int) -> Unit,
) {
    var keyword by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    LaunchedEffect(keywords.size) {
        scrollState.animateScrollTo(Int.MAX_VALUE)
    }

    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        Text(
            text = stringResource(R.string.subscribe_keyword_title),
            style = Heading3,
            color = White
        )

        Text(
            text = stringResource(R.string.subscribe_keyword_subtitle),
            style = Body1,
            color = Gray06,
            modifier = Modifier.padding(top = 8.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 20.dp)
                .verticalScroll(scrollState)
        ) {
            keywords.forEachIndexed { i, keyword ->
                KeywordBorderActionChip(
                    text = keyword,
                    index = i,
                    onKeywordDelete = onKeywordDelete
                )
            }

            KeyLinkOnBoardingTextField(
                value = keyword,
                onValueChange = { keyword = it },
                hint = "예) 매쉬업",
                fontSize = 14.sp,
                onClickDone = {
                    onKeywordAdd(keyword)
                    keyword = ""
                },
                minLength = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubscribeKeywordScreenPreview() {
    SsamDTheme {
        SubscribeKeywordScreen(
            subscribeKeywords = emptyList(),
            onShowSnackbar = { _, _ -> },
            onBackClick = {},
            onSaveButtonClick = {},
            onAddKeyword = {},
            onDeleteKeyword = {},
        )
    }
}
