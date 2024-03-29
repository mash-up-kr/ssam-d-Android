package com.mashup.presentation.feature.reply

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkTextField

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/22
 */
private const val MAX_LENGTH = 300

@Composable
fun ReplyContent(
    reply: String,
    onReplyTextChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onLengthOver: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            KeyLinkTextField(
                modifier = Modifier.fillMaxWidth(),
                value = reply,
                onValueChange = { reply ->
                    if (reply.length >= MAX_LENGTH) onLengthOver()
                    else onReplyTextChange(reply)
                },
                hint = stringResource(id = R.string.hint_signal_content),
                hintAlign = TextAlign.Start,
                maxLength = 300
            )
        }
        KeyLinkButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 20.dp),
            text = stringResource(id = R.string.button_send_reply),
            onClick = {
                focusManager.clearFocus()
                onSendClick()
            },
            enable = reply.isNotEmpty()
        )
    }
}


