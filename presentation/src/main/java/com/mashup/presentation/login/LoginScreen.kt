package com.mashup.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.*
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkMintText
import com.mashup.presentation.ui.theme.Gray06
import com.mashup.presentation.ui.theme.White

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    val pagerState = rememberPagerState(0)

    LaunchedEffect(loginViewModel.currentPage) {
        pagerState.animateScrollToPage(loginViewModel.currentPage)
    }
    
    HorizontalPager(
        modifier = Modifier.fillMaxSize(),
        pageCount = 2,
        state = pagerState,
        userScrollEnabled = false
    ) { page ->
        when (page) {
            0 -> LoginContentScreen (
                onLoginButtonClicked = loginViewModel::handleKakaoLogin
            )
            1 -> LoginCompletionScreen (
                onStartButtonClicked = loginViewModel::handleKakaoLogin
            )
        }
    }
}

@Composable
fun LoginContentScreen(
    onLoginButtonClicked: () -> Unit
) {
    Box {
        LoginBackground()

        LoginContainer(modifier = Modifier.padding(top = 120.dp, bottom = 48.dp)) {
            LoginTitle(modifier = Modifier.padding(bottom = 24.dp))

            LoginPlanetLottie(modifier = Modifier
                .fillMaxWidth()
                .height(279.dp))

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(53.dp)
            ) {
                KakaoLoginButton(modifier = Modifier.padding(horizontal = 4.dp)) {
                    onLoginButtonClicked()
                }

                LoginGuideText()
            }
        }
    }
}

@Composable
private fun LoginBackground() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.img_space),
        contentDescription = stringResource(R.string.login_description_space),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun LoginContainer(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Color.Transparent
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Composable
private fun LoginTitle(modifier: Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier
                .height(61.dp)
                .width(209.dp),
            painter = painterResource(R.drawable.img_keylink),
            contentDescription = stringResource(R.string.login_description_keylink)
        )

        KeyLinkMintText(text = stringResource(R.string.login_title))
    }
}

@Composable
private fun LoginPlanetLottie(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.lottie_splash_planet)
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}

@Composable
private fun KakaoLoginButton(modifier: Modifier = Modifier, onLoginButtonClicked: () -> Unit) {
    Image(
        modifier = modifier
            .height(52.dp)
            .width(312.dp)
            .clickable { onLoginButtonClicked() },
        painter = painterResource(R.drawable.img_kakao_login),
        contentDescription = stringResource(R.string.login_description_kakao_btn)
    )
}

@Composable
private fun LoginGuideText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.login_privacy_policy),
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 18.sp,
            color = Gray06
        )
    )
}

@Composable
fun LoginCompletionScreen(onStartButtonClicked: () -> Unit) {
    Box {
        LoginBackground()

        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.img_blueplanet),
            contentDescription = stringResource(R.string.login_description_blueplanet)
        )

        LoginContainer(modifier = Modifier.padding(top = 160.dp, bottom = 72.dp, start = 20.dp, end = 20.dp)) {
            LoginCompletionText(modifier = Modifier.weight(1f), nickname = "test")

            KeyLinkButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.login_completion_btn),
                onClick = onStartButtonClicked
            )
        }
    }
}

@Composable
fun LoginCompletionText(modifier: Modifier = Modifier, nickname: String) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = nickname + stringResource(R.string.login_completion_title),
        style = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            color = White
        )
    )
}
