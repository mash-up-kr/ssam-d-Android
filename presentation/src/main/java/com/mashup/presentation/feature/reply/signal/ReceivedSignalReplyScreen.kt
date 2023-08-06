package com.mashup.presentation.feature.reply.signal

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mashup.presentation.R
import com.mashup.presentation.feature.reply.ReplyContent
import com.mashup.presentation.ui.common.KeyLinkContentLengthDialog
import com.mashup.presentation.ui.common.KeyLinkGoBackDialog
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.Body2
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/22
 */
@Composable
fun ReceivedSignalReplyRoute(
    signalId: Long,
    onClickBack: () -> Unit,
    navigateToHome: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ReceivedSignalReplyViewModel = hiltViewModel()
) {
    val event by viewModel.eventFlow.collectAsStateWithLifecycle(initialValue = Idle)
    var reply by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(event) {
        when (event) {
            SendReplySuccess -> navigateToHome()
            is SendReplyFailed -> {
                onShowSnackbar(
                    (event as SendReplyFailed).message.orEmpty(),
                    SnackbarDuration.Short
                )
            }
            else -> {}
        }
    }

    ReceivedSignalReplyScreen(
        reply = reply,
        onReplyTextChange = { reply = it },
        onClickBack = onClickBack,
        onSendClick = {
            viewModel.sendReceivedSignalReply(signalId, reply)
        },
        modifier = modifier
    )
}

@Composable
private fun ReceivedSignalReplyScreen(
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