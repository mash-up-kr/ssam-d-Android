package com.mashup.presentation.detail.message.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/03
 */
@Composable
fun MessageDetailScreen(
    modifier: Modifier = Modifier,
    onUpButtonClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onSendReplyClick: () -> Unit = {}
) {
    val matchedKeywords = listOf("매쉬업", "일상", "디자인", "IT", "취준", "일상", "디자인", "IT", "취준")

    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(
                onClickBack = {
                    onUpButtonClick()
                },
                menuAction = {
                    Icon(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .clickable {
                                onMenuClick()
                            },
                        painter = painterResource(id = R.drawable.ic_declare_24),
                        tint = White,
                        contentDescription = stringResource(R.string.content_description_report)
                    )
                }

            )
        },
    ) { paddingValues ->
        val contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding() + 16.dp,
            start = 20.dp,
            end = 20.dp
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MessageDetailContainer(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                othersName = "연날리기",
                date = "2023년 5월 30일",
                message = "이번주 불참해서 공지를 못 들음...다음 전체회의에 준비할 내용이 어떤거였죠?",
                matchedKeywords = matchedKeywords
            )

            KeyLinkRoundButton(
                modifier = Modifier.padding(top = 48.dp, bottom = 42.dp),
                text = stringResource(R.string.button_send_reply),
                onClick = {
                    onSendReplyClick()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageScreenPreview() {
    SsamDTheme {
        MessageDetailScreen()
    }
}