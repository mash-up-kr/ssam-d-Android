package com.mashup.presentation.onboarding

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.Gray02
import com.mashup.presentation.ui.theme.Gray07
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White


@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: OnBoardingViewModel = viewModel()

    Column(modifier = modifier.fillMaxSize()) {
        KeyLinkToolbar(
            onClickBack = {
                if (viewModel.currentPage != 0) viewModel.currentPage--
                /*
                    현재 페이지에 따라서 back버튼 동작이 다름
                    첫번째 페이지는 로그인페이지로 감
                    두번째 페이지는 첫번째 온보딩 페이지로 감.
                */
            }
        )
        OnBoardingContent(modifier, viewModel)
    }
}

@Composable
fun OnBoardingContent(
    modifier: Modifier,
    viewModel: OnBoardingViewModel
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        OnBoardingPager(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            currentPage = viewModel.currentPage
        )

        KeyLinkButton(modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
            text = if (viewModel.currentPage == 0) "다음" else "완료",
            onClick = { if (viewModel.currentPage != 1) viewModel.currentPage++ })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPager(
    currentPage: Int,
    modifier: Modifier
) {
    val pagerState = rememberPagerState(0)
    LaunchedEffect(currentPage) {
        pagerState.animateScrollToPage(currentPage)
    }
    HorizontalPager(
        modifier = modifier,
        pageCount = 2,
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        when (page) {
            0 -> NicknameScreen()
            1 -> KeywordScreen()
        }
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
fun KeywordScreen() {
    var chips by remember { mutableStateOf(emptyList<String>()) }
    var keyword by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        KeyLinkMintText(text = "대화하고 싶은 키워드를 적어주세요")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "매칭에 활용돼요! 최대 10개 등록 가능", fontSize = 14.sp, color = Gray07)
        Spacer(modifier = Modifier.height(20.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            for (chip in chips) {
                KeywordChip(text = chip)
            }

            KeyLinkOnBoardingTextField(
                modifier = Modifier.padding(vertical = 12.dp),
                value = keyword,
                onValueChange = { keyword = it },
                hint = if (chips.isEmpty()) "예) #매쉬업 #운동" else "",
                fontSize = 32.sp,
                onClickDone = {
                    chips = chips + keyword
                    keyword = ""
                }
            )
        }

    }
}


@Composable
fun KeywordChip(text: String) {
    Box(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .background(Gray02, shape = RoundedCornerShape(4.dp))
            .padding(vertical = 8.dp, horizontal = 20.dp)
    ) {
        Text(
            text = "#$text",
            modifier = Modifier.align(Alignment.Center),
            color = White,
            fontSize = 32.sp
        )
    }
}

@Preview
@Composable
fun PreviewOnBoardingScreen() {
    SsamDTheme {
        OnBoardingScreen(modifier = Modifier)
    }
}