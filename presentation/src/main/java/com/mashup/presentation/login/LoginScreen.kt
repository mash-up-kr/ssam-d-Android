package com.mashup.presentation.login

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkMintText

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

        LoginContainer(modifier = Modifier.padding(vertical = 120.dp)) {
            LoginTitle(modifier = Modifier.padding(bottom = 24.dp))

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.img_planet),
                contentDescription = stringResource(R.string.login_description_planet)
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .height(52.dp)
                    .width(312.dp)
                    .clickable { onLoginButtonClicked() },
                painter = painterResource(R.drawable.img_kakao_login),
                contentDescription = stringResource(R.string.login_description_kakao_btn)
            )
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
        verticalArrangement = Arrangement.spacedBy(4.dp)
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
fun LoginCompletionScreen(onStartButtonClicked: () -> Unit) {
    Box {
        LoginBackground()

        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.img_blueplanet),
            contentDescription = stringResource(R.string.login_description_blueplanet)
        )

        LoginContainer(modifier = Modifier.padding(top = 155.dp, bottom = 48.dp, start = 20.dp, end = 20.dp)) {
            KeyLinkMintText(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.login_completion_title)
            )

            KeyLinkButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.login_completion_btn),
                onClick = onStartButtonClicked
            )
        }
    }
}
