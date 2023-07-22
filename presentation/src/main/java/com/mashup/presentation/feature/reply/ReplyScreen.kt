package com.mashup.presentation.feature.reply

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.R
import com.mashup.presentation.feature.detail.ChatDetailViewModel
import com.mashup.presentation.feature.detail.chat.compose.MessageReplyUiState
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.Body2
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White


/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/10
 */

@Composable
fun ReplyRoute(
    roomId: Long,
    onClickBack: () -> Unit,
    navigateToChat: (Long) -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.replyUiState.collectAsStateWithLifecycle()
    var reply by rememberSaveable { mutableStateOf("") }

    ReplyScreen(
        roomId = roomId,
        reply = reply,
        modifier = modifier,
        onReplyTextChange = { reply = it },
        onClickBack = onClickBack,
        onSendClick = {
            viewModel.reply(roomId, reply)
        },
        onShowSnackbar = onShowSnackbar,
        uiState = uiState,
        navigateToChat = navigateToChat
    )
}

@Composable
private fun ReplyScreen(
    roomId: Long,
    reply: String,
    onReplyTextChange: (String) -> Unit,
    onClickBack: () -> Unit,
    onSendClick: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    uiState : MessageReplyUiState,
    navigateToChat: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showGoBackDialog by rememberSaveable { mutableStateOf(false) }
    var showLengthOverDialog by rememberSaveable { mutableStateOf(false) }

    BackHandler(true) {
        showGoBackDialog = true
    }

    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(
                title = {
                    Text(
                        text = stringResource(R.string.toolbar_reply),
                        style = Body2,
                        color = White
                    )
                },
                onClickBack = { showGoBackDialog = true }
            )
        }
    ) { innerPaddingValues ->
        when(uiState) {
            is MessageReplyUiState.Loading -> KeyLinkLoading()
            is MessageReplyUiState.SaveSuccess -> {
                navigateToChat(roomId)
            }
            else -> {
                ReplyContent(
                    modifier = Modifier.padding(innerPaddingValues),
                    reply = reply,
                    onReplyTextChange = onReplyTextChange,
                    onSendClick = onSendClick,
                    onLengthOver = { showLengthOverDialog = true },
                    onShowSnackbar = onShowSnackbar
                )
            }
        }

        if (showGoBackDialog) {
            KeyLinkGoBackDialog(
                onDismissRequest = { showGoBackDialog = false },
                onCloseClick = { showGoBackDialog = false },
                onGoBackClick = onClickBack
            )
        }

        if (showLengthOverDialog) {
            KeyLinkContentLengthDialog(
                onDismissRequest = { showLengthOverDialog = false },
                onButtonClick = { showLengthOverDialog = false }
            )
        }
    }
}

@Composable
fun ReplyContent(
    reply: String,
    onReplyTextChange: (String) -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onSendClick: () -> Unit,
    onLengthOver: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val snackbarMessage = stringResource(R.string.snackbar_reply)

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        KeyLinkTextField(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            value = reply,
            onValueChange = { reply ->
                if (reply.length >= MAX_LENGTH) onLengthOver()
                else onReplyTextChange(reply)
            },
            hint = stringResource(id = R.string.hint_signal_content),
            hintAlign = TextAlign.Start,
            maxLength = 300
        )
        KeyLinkButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 20.dp)
                .padding(bottom = 48.dp),
            text = stringResource(id = R.string.button_send_reply),
            onClick = {
                onShowSnackbar(snackbarMessage, SnackbarDuration.Short)
                onSendClick()
            },
            enable = reply.isNotEmpty()
        )
    }
}

private const val MAX_LENGTH = 300

@Preview
@Composable
private fun ReplyScreenPreview() {
    SsamDTheme {
        ReplyRoute(
            roomId = 1,
            onClickBack = {},
            navigateToChat = {},
            onShowSnackbar = { _, _ -> }
        )
    }
}