package com.mashup.presentation.signal.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.Gray06
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/21
 */
@Composable
fun SignalKeywordScreen(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {},
    navigateToComplete: () -> Unit = {}
) {
    val keywords = remember { mutableStateListOf<String>() }
    var showGoBackDialog by remember { mutableStateOf(false) }

    BackHandler(true) {
        showGoBackDialog = true
    }

    Column(modifier = modifier.fillMaxSize()) {
        KeyLinkToolbar(onClickBack = { showGoBackDialog = true })
        KeywordScreen(
            isLoading = isLoading,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp)
                .weight(1f),
            afterLoadingContent = {
                SignalKeyword(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                        .weight(1f),
                    keywords = keywords,
                    onKeywordAdd = {
                        keywords.add(it)
                    },
                    onKeywordDelete = {
                        keywords.removeAt(it)
                    },
                )
            }
        )
        KeyLinkButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp, start = 20.dp, end = 20.dp),
            text = stringResource(R.string.button_send_signal),
            enable = !(isLoading || keywords.isEmpty()),
            onClick = { navigateToComplete() }
        )
    }

    if (showGoBackDialog) {
        KeyLinkGoBackDialog(
            onDismissRequest = { },
            onGoBackClick = { navigateUp() },
            onCloseClick = { showGoBackDialog = false }
        )
    }
}

@Composable
fun KeywordScreen(
    isLoading: Boolean,
    afterLoadingContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (isLoading) {
        ShimmerScreen(modifier = modifier)
    } else {
        afterLoadingContent()
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SignalKeyword(
    keywords: MutableList<String>,
    onKeywordAdd: (String) -> Unit,
    onKeywordDelete: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    var keyword by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    LaunchedEffect(keywords.size) {
        scrollState.animateScrollTo(Int.MAX_VALUE)
    }

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.signal_keyword_title),
            style = TextStyle(
                fontSize = 22.sp,
                color = White,
                fontWeight = FontWeight.Bold
            ),
        )

        Text(
            text = stringResource(R.string.signal_keyword_subtitle),
            style = TextStyle(
                fontSize = 16.sp,
                color = Gray06
            ),
            modifier = Modifier.padding(top = 8.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 28.dp)
                .verticalScroll(scrollState)
        ) {
            keywords.forEachIndexed { i, keyword ->
                KeywordBorderChip(
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
fun SignalKeywordLoadingScreenPreview() {
    SsamDTheme {
        SignalKeywordScreen(isLoading = true)
    }
}

@Preview(showBackground = true)
@Composable
fun SignalKeywordScreenPreview() {
    SsamDTheme {
        SignalKeywordScreen(isLoading = false)
    }
}