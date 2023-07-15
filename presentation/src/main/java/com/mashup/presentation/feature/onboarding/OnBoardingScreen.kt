package com.mashup.presentation.feature.onboarding

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.Gray07
import com.mashup.presentation.ui.theme.SsamDTheme


@Composable
fun OnBoardingRoute(
    modifier: Modifier = Modifier,
    navigateToNotificationPermission: () -> Unit,
    navigateToHome: () -> Unit,
    finishActivity: () -> Unit
) {
    OnBoardingScreen(
        modifier = modifier,
        navigateToNotificationPermission = navigateToNotificationPermission,
        finishActivity = finishActivity,
        navigateToHome = navigateToHome
    )
}

@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navigateToNotificationPermission: () -> Unit,
    navigateToHome: () -> Unit,
    finishActivity: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showGoFirstDialog by remember { mutableStateOf(false) }

    BackHandler(true) {
        showGoFirstDialog = true
    }

    Scaffold(
        topBar = {
            KeyLinkToolbar(
                onClickBack = { showGoFirstDialog = true }
            )
        },
        backgroundColor = Black
    ) {
        when (uiState) {
            OnBoardingViewModel.UiState.Loading -> {
                KeyLinkLoading()
            }
            OnBoardingViewModel.UiState.SaveSuccess -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    navigateToNotificationPermission()
                } else {
                    navigateToHome()
                }
            }
            is OnBoardingViewModel.UiState.Editing -> {
                OnBoardingContent(
                    modifier.padding(it),
                    keywords = (uiState as OnBoardingViewModel.UiState.Editing).keywords,
                    addKeyword = viewModel::addKeyword,
                    removeKeyword = viewModel::removeKeyword,
                    saveKeywords = viewModel::saveKeywords,
                )
            }
            is OnBoardingViewModel.UiState.SaveFailed -> {
                // TODO: show error Snackbar
            }
        }
    }
    if (showGoFirstDialog) {
        KeyLinkGoFirstDialog(
            onDismissRequest = {},
            onPositiveClick = {
                finishActivity()
                showGoFirstDialog = false
            },
            onNegativeClick = { showGoFirstDialog = false }
        )
    }
}

@Composable
fun OnBoardingContent(
    modifier: Modifier,
    keywords: List<String>,
    addKeyword: (String) -> Unit,
    removeKeyword: (Int) -> Unit,
    saveKeywords: (List<String>) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        KeywordScreen(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            keywords = keywords,
            onKeywordAdd = addKeyword,
            onKeywordDelete = removeKeyword
        )
        KeyLinkButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            text = stringResource(id = R.string.onboarding_keywords_complete),
            onClick = {
                saveKeywords(keywords)
            },
            enable = keywords.isNotEmpty()
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KeywordScreen(
    modifier: Modifier,
    keywords: List<String>,
    onKeywordAdd: (String) -> Unit,
    onKeywordDelete: (Int) -> Unit
) {
    var keyword by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    LaunchedEffect(keywords.size) {
        scrollState.animateScrollTo(Int.MAX_VALUE)
    }

    Column(
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        KeyLinkMintText(text = stringResource(id = R.string.onboarding_keywords_input))
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.onboarding_keywords_input_description),
            fontSize = 14.sp,
            color = Gray07
        )
        Spacer(modifier = Modifier.height(4.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            keywords.forEachIndexed { i, keyword ->
                KeywordActionChip(
                    text = keyword,
                    index = i,
                    onKeywordDelete = onKeywordDelete
                )
            }

            KeyLinkOnBoardingTextField(
                value = keyword,
                onValueChange = { keyword = it },
                hint = stringResource(id = R.string.onboarding_keywords_chip_hint),
                fontSize = 24.sp,
                onClickDone = {
                    onKeywordAdd(keyword)
                    keyword = ""
                },
                minLength = 1
            )
        }

    }
}

@Preview
@Composable
fun PreviewOnBoardingScreen() {
    SsamDTheme {
        OnBoardingScreen(
            modifier = Modifier,
            navigateToNotificationPermission = {},
            finishActivity = {},
            navigateToHome = {}
        )
    }
}