package com.mashup.presentation.detail.message.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.common.KeywordChip
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/03
 */
@Composable
fun MessageDetailScreen(
    modifier: Modifier = Modifier,
    onNavigateUpClick: () -> Unit = {},
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
                    onNavigateUpClick()
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

@Composable
fun MessageDetailContainer(
    othersName: String,
    date: String,
    message: String,
    matchedKeywords: List<String>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier.verticalScroll(scrollState)) {
        MessageInfo(modifier = Modifier, othersName = othersName, date = date)

        MessageDetailContent(
            modifier = Modifier.padding(top = 16.dp),
            message = message
        )

        MatchedKeywordContainer(
            modifier = Modifier.padding(top = 52.dp),
            matchedKeywords = matchedKeywords
        )
    }
}


@Composable
fun MessageInfo(
    othersName: String,
    date: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Red)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = othersName,
                style = Title1,
                color = White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = date,
                style = Caption1,
                color = Gray06
            )
        }
    }
}

@Composable
fun MessageDetailContent(
    message: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = message,
        style = Body1,
        color = Gray10
    )
}

@Composable
fun MatchedKeywordContainer(
    matchedKeywords: List<String>,
    modifier: Modifier = Modifier
) {
    MatchedKeywordHeader(
        modifier = modifier,
        matchedKeywords = matchedKeywords.size
    )
    MatchedKeywordsContent(matchedKeywords = matchedKeywords)
}

@Composable
fun MatchedKeywordHeader(
    matchedKeywords: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_frequency_mint_16),
            tint = Mint,
            contentDescription = stringResource(id = R.string.content_description_chat_item_frequency)
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = stringResource(
                id = R.string.message_detail_matched_signal_count,
                matchedKeywords
            ),
            style = Title2,
            color = Mint
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MatchedKeywordsContent(
    matchedKeywords: List<String>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = Modifier.padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        matchedKeywords.forEach { keyword ->
            KeywordChip(
                modifier = Modifier.padding(top = 8.dp),
                keyword = keyword
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

@Preview(showBackground = true)
@Composable
private fun MessageInfoPreview() {
    SsamDTheme {
        Surface(color = Black) {
            MessageInfo(othersName = "연날리기", date = "2023년 5월 30일")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MatchedKeywordContentPreview() {
    val matchedKeywords = listOf("매쉬업", "일상", "디자인", "IT", "취준", "일상", "디자인", "IT", "취준")
    SsamDTheme {
        Surface(color = Black) {
            MatchedKeywordContainer(matchedKeywords)
        }
    }
}