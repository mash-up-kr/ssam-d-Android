package com.mashup.presentation.onboarding

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.Gray02
import com.mashup.presentation.ui.theme.Gray07
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navigateToNotificationPermission: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val viewModel: OnBoardingViewModel = viewModel()

    Column(modifier = modifier.fillMaxSize()) {
        KeyLinkToolbar(
            onClickBack = {}
        )
        OnBoardingContent(
            modifier,
            keywords = viewModel.keywords,
            addKeyword = viewModel::addKeyword,
            removeKeyword = viewModel::removeKeyword,
            saveKeywords = viewModel::saveKeywords,
            navigateToNotificationPermission = navigateToNotificationPermission,
            coroutineScope = coroutineScope
        )
    }

    LaunchedEffect(key1 = viewModel.uiEvent) {
        coroutineScope.launch {
            viewModel.uiEvent.collectLatest {
                when (it) {
                    is OnBoardingViewModel.UiEvent.SuccessSave -> {
                        navigateToNotificationPermission()
                    }
                    else -> {}
                }
            }
        }
    }
}

@Composable
fun OnBoardingContent(
    modifier: Modifier,
    keywords: MutableList<String>,
    addKeyword: (String) -> Unit,
    removeKeyword: (Int) -> Unit,
    saveKeywords: suspend (List<String>) -> Unit,
    navigateToNotificationPermission: () -> Unit,
    coroutineScope: CoroutineScope
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
                coroutineScope.launch {
                    saveKeywords(keywords)
                }
            },
            enable = keywords.isNotEmpty()
        )
    }
}


@Composable
fun NicknameScreen() {
    var nickname by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        KeyLinkMintText(text = "사용할 닉네임을 입력해주세요")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "10글자 이내", fontSize = 14.sp, color = Gray07)
        Spacer(modifier = Modifier.height(56.dp))
        KeyLinkOnBoardingTextField(
            value = nickname,
            onValueChange = { nickname = it },
            hint = "닉네임 입력",
            fontSize = 32.sp,
            onClickDone = { /* 키보드 닫기 */ },
            maxLength = 10
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KeywordScreen(
    modifier: Modifier,
    keywords: MutableList<String>,
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
            navigateToNotificationPermission = {})
    }
}