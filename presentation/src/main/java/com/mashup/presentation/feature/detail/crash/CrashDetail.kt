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
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.R
import com.mashup.presentation.feature.detail.message.compose.MessageInfo
import com.mashup.presentation.feature.reply.crash.CrashReplyUiEvent
import com.mashup.presentation.ui.common.KeyLinkLoading
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
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CrashDetailViewModel = hiltViewModel()
) {
    val crashDetailUiState by viewModel.crashUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCrashDetail(crashId = crashId)
    }

    CrashDetailScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onReportMenuClick = onReportMenuClick,
        onReplyButtonClick = {
            onReplyButtonClick(crashId)
        },
        onShowSnackbar = onShowSnackbar,
        crashDetailUiState = crashDetailUiState
    )
}

@Composable
fun CrashDetailScreen(
    onBackClick: () -> Unit,
    onReportMenuClick: () -> Unit,
    onReplyButtonClick: (Long) -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    crashDetailUiState: CrashDetailUiState
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
        when (crashDetailUiState) {
            is CrashDetailUiState.Loading -> KeyLinkLoading()
            is CrashDetailUiState.Success -> {
                CrashDetailContent(
                    contentPadding = contentPadding,
                    crash = crashDetailUiState.crash,
                    onReplyButtonClick = onReplyButtonClick
                )
            }
            is CrashDetailUiState.Error -> {
                crashDetailUiState.message?.let {
                    onShowSnackbar(it, SnackbarDuration.Short)
                }
            }
        }
    }
}

@Composable
private fun CrashDetailContent(
    contentPadding: PaddingValues,
    crash: CrashUiModel,
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
            othersName = crash.nickname,
            date = crash.receivedTime,
            message = crash.content,
            profileImage = crash.profileImage
        )


        KeyLinkRoundButton(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 40.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally),
            text = stringResource(R.string.button_send_reply),
            onClick = { onReplyButtonClick(crash.id) }
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
