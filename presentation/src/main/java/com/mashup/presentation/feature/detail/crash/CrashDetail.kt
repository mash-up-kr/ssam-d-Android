package com.mashup.presentation.feature.detail.crash

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
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.getDisplayedDateWithDay
import com.mashup.presentation.common.extension.visible
import com.mashup.presentation.feature.detail.message.compose.MessageInfo
import com.mashup.presentation.feature.detail.message.model.MessageDetailUiModel
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.Body1
import com.mashup.presentation.ui.theme.Gray10
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/05
 */
@Composable
fun CrashDetailRoute(
    crashId: Long,
    onBackClick: () -> Unit,
    onReportMenuClick: () -> Unit,
    onReplyButtonClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    CrashDetailScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onReportMenuClick = onReportMenuClick,
        onReplyButtonClick = {
            onReplyButtonClick(crashId)
        }
    )
}

@Composable
fun CrashDetailScreen(
    onBackClick: () -> Unit,
    onReportMenuClick: () -> Unit,
    onReplyButtonClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(
                onClickBack = onBackClick,
                menuAction = {
                    Icon(
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .clickable {
                                onReportMenuClick()
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
            start = 20.dp
        )
//        when (messageDetailUiState) {
//            is MessageDetailUiState.Loading -> KeyLinkLoading()
//            is MessageDetailUiState.Success -> {
//
//            }
//            is MessageDetailUiState.Failure -> {}
//        }
        CrashDetailContent(
            contentPadding = contentPadding,
//            crashDetail = messageDetailUiState.messageDetail,
            onReplyButtonClick = onReplyButtonClick
        )
    }
}

@Composable
private fun CrashDetailContent(
    contentPadding: PaddingValues,
//    crashDetail: MessageDetailUiModel,
    onReplyButtonClick: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CrashDetailContainer(
            modifier = Modifier
                .weight(1f)
                .padding(end = 20.dp),
            othersName = "슈퍼니카",
            date = "202020202020",
            message = "이번주 불참해서 공지를 못 들음 뭐시기 뭐시기 울랄라",
            profileImage = ""
        )


        KeyLinkRoundButton(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 40.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.button_send_reply),
            onClick = { onReplyButtonClick(0 /* CrashId */) }
        )
    }
}


@Composable
fun CrashDetailContainer(
    othersName: String,
    date: String,
    message: String,
    profileImage: String,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier.verticalScroll(scrollState)) {
        MessageInfo(modifier = Modifier, othersName = othersName, date = date, profileImage = profileImage)

        CrashDetailContent(
            modifier = Modifier.padding(top = 16.dp),
            message = message
        )
    }
}

@Composable
fun CrashDetailContent(
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
