package com.mashup.presentation.feature.signal.send.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.R
import com.mashup.presentation.feature.signal.send.KeywordUiState
import com.mashup.presentation.feature.signal.send.SignalUiEvent
import com.mashup.presentation.feature.signal.send.SignalViewModel
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.Gray06
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White
import okhttp3.internal.toImmutableList

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/21
 */
@Composable
fun SignalKeywordRoute(
    content: String,
    onBackClick: () -> Unit,
    onSendSuccess: () -> Unit,
    onSendFailed: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignalViewModel = hiltViewModel()
) {
    val keywordsUiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    val sendSignalEvent by viewModel.eventFlow.collectAsStateWithLifecycle(SignalUiEvent.Nothing)
    val keywords = viewModel.keywordListState.toImmutableList()

    LaunchedEffect(Unit) {
        viewModel.getRecommendKeywords(content)
    }

    LaunchedEffect(sendSignalEvent) {
        when (sendSignalEvent) {
            SignalUiEvent.SendSignalSuccess -> onSendSuccess()
            is SignalUiEvent.Error -> onSendFailed()
            else -> {}
        }
    }

    SignalKeywordScreen(
        modifier = modifier,
        keywords = keywords,
        onKeywordAdd = viewModel::addKeyword,
        onKeywordDelete = viewModel::deleteKeyword,
        onDialogBackClick = onBackClick,
        onSendClick = { viewModel.sendSignal(content) },
        keywordUiState = keywordsUiState,
    )
}

@Composable
fun SignalKeywordScreen(
    keywords: List<String>,
    onDialogBackClick: () -> Unit,
    onSendClick: () -> Unit,
    onKeywordAdd: (String) -> Unit,
    onKeywordDelete: (Int) -> Unit,
    modifier: Modifier = Modifier,
    keywordUiState: KeywordUiState = KeywordUiState.Loading
) {
    var keyword by rememberSaveable { mutableStateOf("") }
    var showGoBackDialog by remember { mutableStateOf(false) }

    BackHandler(true) {
        showGoBackDialog = true
    }

    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(
                onClickBack = { showGoBackDialog = true }
            )
        }
    ) { paddingValues ->
        val innerPaddingValues = PaddingValues(
            top = paddingValues.calculateTopPadding() + 8.dp,
            start = 20.dp,
            end = 20.dp
        )

        when (keywordUiState) {
            is KeywordUiState.Loading -> {
                Column(modifier = Modifier.padding(innerPaddingValues)) {
                    ShimmerScreen(
                        modifier = Modifier.weight(1f)
                    )
                    KeyLinkButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 48.dp),
                        text = stringResource(R.string.button_send_signal),
                        enable = false,
                        onClick = onSendClick
                    )
                }
            }
            is KeywordUiState.Success -> {
                Column(modifier = Modifier.padding(innerPaddingValues)) {
                    SignalKeyword(
                        modifier = Modifier.weight(1f),
                        keywords = keywords,
                        onKeywordAdd = { onKeywordAdd(it) },
                        onKeywordDelete = { onKeywordDelete(it) },
                        onKeywordChange = { keyword = it },
                        onRefreshValue = { keyword = "" },
                        keyword = keyword
                    )
                    KeyLinkButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 48.dp),
                        text = stringResource(R.string.button_send_signal),
                        enable = keywords.isNotEmpty(),
                        onClick = onSendClick
                    )
                }
            }
            is KeywordUiState.Error -> {}
            else -> {}
        }

        if (showGoBackDialog) {
            KeyLinkGoBackDialog(
                onDismissRequest = { },
                onGoBackClick = onDialogBackClick,
                onCloseClick = { showGoBackDialog = false }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SignalKeyword(
    keyword: String,
    keywords: List<String>,
    onKeywordChange: (String) -> Unit,
    onKeywordAdd: (String) -> Unit,
    onKeywordDelete: (Int) -> Unit,
    onRefreshValue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(keywords.size) {
        scrollState.animateScrollTo(Int.MAX_VALUE)
    }

    Column(modifier = modifier.fillMaxSize()) {
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
                KeywordBorderActionChip(
                    text = keyword,
                    index = i,
                    onKeywordDelete = onKeywordDelete
                )
            }

            KeyLinkOnBoardingTextField(
                value = keyword,
                onValueChange = onKeywordChange,
                hint = stringResource(R.string.text_field_hint),
                fontSize = 14.sp,
                onClickDone = {
                    onKeywordAdd(keyword)
                    onRefreshValue()
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
        SignalKeywordScreen(
            onDialogBackClick = {},
            onSendClick = {},
            keywords = emptyList(),
            onKeywordAdd = {},
            onKeywordDelete = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignalKeywordScreenPreview() {
    SsamDTheme {
        SignalKeywordScreen(
            onDialogBackClick = {},
            onSendClick = {},
            keywords = listOf(),
            onKeywordAdd = {},
            onKeywordDelete = {},
            keywordUiState = KeywordUiState.Success
        )
    }
}