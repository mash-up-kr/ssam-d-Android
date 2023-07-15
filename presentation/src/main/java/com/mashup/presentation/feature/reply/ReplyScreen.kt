package com.mashup.presentation.feature.reply

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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.Body2
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/10
 */

/**
 * 임시 뷰모델
 */
@HiltViewModel
class SampleViewModel @Inject constructor() : ViewModel() {
    fun sendReply(reply: String) {
        viewModelScope.launch {
            // Call UseCase
        }
    }
}

@Composable
fun ReplyRoute(
    onClickBack: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SampleViewModel = hiltViewModel()
) {
    var reply by rememberSaveable { mutableStateOf("") }

    ReplyScreen(
        reply = reply,
        modifier = modifier,
        onReplyTextChange = { reply = it },
        onClickBack = onClickBack,
        onSendClick = { viewModel.sendReply(reply) },
        onShowSnackbar = onShowSnackbar
    )
}

@Composable
private fun ReplyScreen(
    reply: String,
    onReplyTextChange: (String) -> Unit,
    onClickBack: () -> Unit,
    onSendClick: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showGoBackDialog by rememberSaveable { mutableStateOf(false) }
    var showLengthOverDialog by rememberSaveable { mutableStateOf(false) }

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
            onShowSnackbar = onShowSnackbar
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
            onClickDone = { /*TODO*/ },
            maxLength = 300
        )
        KeyLinkButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 20.dp)
                .padding(bottom = 48.dp),
            text = stringResource(id = R.string.next),
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
            onClickBack = {},
            onShowSnackbar = { _, _ -> }
        )
    }
}