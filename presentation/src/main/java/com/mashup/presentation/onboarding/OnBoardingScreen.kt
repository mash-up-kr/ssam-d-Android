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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.Gray02
import com.mashup.presentation.ui.theme.Gray07
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White


@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    navigateToNotificationPermission: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        KeyLinkToolbar(
            onClickBack = {}
        )
        OnBoardingContent(modifier, navigateToNotificationPermission)
    }
}

@Composable
fun OnBoardingContent(
    modifier: Modifier,
    navigateToNotificationPermission: () -> Unit
) {
    val keywords = remember { mutableStateListOf<String>() }

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
            onKeywordAdd = {
                keywords.add(it)
            },
            onKeywordDelete = {
                keywords.removeAt(it)
            }
        )
        KeyLinkButton(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            text = "모두 입력했어요",
            onClick = {
                /**
                 * 일단 navigate 되게 만들어놨고, onboarding api 연결하는걸로 수정 예정
                 */
                navigateToNotificationPermission()
            },
            enable = keywords.size > 0
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
        KeyLinkMintText(text = "대화하고 싶은 키워드를 적어주세요")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "입력한 키워드를 기반으로 매칭해드려요", fontSize = 14.sp, color = Gray07)
        Spacer(modifier = Modifier.height(4.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            keywords.forEachIndexed { i, keyword ->
                KeywordChip(text = keyword, drawBorder = false, index = i, onKeywordDelete)
            }

            KeyLinkOnBoardingTextField(
                value = keyword,
                onValueChange = { keyword = it },
                hint = "예) 매쉬업",
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
        OnBoardingScreen(modifier = Modifier) {}
    }
}