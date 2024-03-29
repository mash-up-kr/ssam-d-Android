package com.mashup.presentation.feature.reply.chat

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.R
import com.mashup.presentation.feature.detail.chat.ChatDetailViewModel
import com.mashup.presentation.feature.detail.chat.compose.MessageReplyUiEvent
import com.mashup.presentation.feature.reply.ReplyContent
import com.mashup.presentation.ui.common.KeyLinkContentLengthDialog
import com.mashup.presentation.ui.common.KeyLinkGoBackDialog
import com.mashup.presentation.ui.common.KeyLinkToolbar
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
fun ChatReplyRoute(
    onClickBack: () -> Unit,
    navigateToChat: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ChatDetailViewModel = hiltViewModel()
) {
    val event by viewModel.eventFlow.collectAsStateWithLifecycle(MessageReplyUiEvent.Idle)
    var reply by rememberSaveable { mutableStateOf("") }

    val snackbarMessage = stringResource(R.string.snackbar_reply)

    LaunchedEffect(event) {
        when (event) {
            is MessageReplyUiEvent.SaveSuccess -> {
                onShowSnackbar(snackbarMessage, SnackbarDuration.Short)
                navigateToChat()
            }
            is MessageReplyUiEvent.Failure -> {
                val errorMessage = (event as MessageReplyUiEvent.Failure).message.orEmpty()
                onShowSnackbar(errorMessage, SnackbarDuration.Short)
            }
            else -> {}
        }
    }

    ChatReplyScreen(
        reply = reply,
        modifier = modifier,
        onReplyTextChange = { reply = it },
        onClickBack = onClickBack,
        onSendClick = { viewModel.reply(reply) },
    )
}

@Composable
private fun ChatReplyScreen(
    reply: String,
    onReplyTextChange: (String) -> Unit,
    onClickBack: () -> Unit,
    onSendClick: () -> Unit,
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
        ReplyContent(
            modifier = Modifier.padding(innerPaddingValues),
            reply = reply,
            onReplyTextChange = onReplyTextChange,
            onSendClick = onSendClick,
            onLengthOver = { showLengthOverDialog = true },
        )

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

@Preview
@Composable
private fun ReplyScreenPreview() {
    SsamDTheme {
        ChatReplyRoute(
            onClickBack = {},
            navigateToChat = {},
            onShowSnackbar = { _, _ -> }
        )
    }
}