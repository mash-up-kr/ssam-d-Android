package com.mashup.presentation.feature.signal.send.compose

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.presentation.R
import com.mashup.presentation.feature.signal.send.SignalViewModel
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkContentLengthDialog
import com.mashup.presentation.ui.common.KeyLinkTextField
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.Body2
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/20
 */
@Composable
fun SignalContentRoute(
    onBackClick: () -> Unit,
    onNextClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignalViewModel = hiltViewModel()
) {
    var signalContent by rememberSaveable { mutableStateOf("") }

    SignalContentScreen(
        modifier = modifier,
        signalContent = signalContent,
        onBackClick = onBackClick,
        onNextClick = { onNextClick(signalContent) },
        onSignalChange = { signalContent = it }
    )
}

@Composable
fun SignalContentScreen(
    signalContent: String,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    onSignalChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var dialogState by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
    ) {
        KeyLinkToolbar(
            title = {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.create_signal),
                    style = Body2,
                    color = White
                )
            },
            onClickBack = onBackClick
        )
        SignalContent(
            modifier = modifier,
            signalContent = signalContent,
            onNextClick = {
                focusManager.clearFocus()
                onNextClick()
            },
            onLengthOver = { dialogState = true },
            onSignalChange = onSignalChange
        )

        if (dialogState) {
            KeyLinkContentLengthDialog(
                onDismissRequest = { /* @TODO */ },
                onButtonClick = { dialogState = false }
            )
        }
    }
}

@Composable
fun SignalContent(
    signalContent: String,
    onNextClick: () -> Unit,
    onLengthOver: () -> Unit,
    onSignalChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            KeyLinkTextField(
                modifier = Modifier.fillMaxWidth(),
                value = signalContent,
                onValueChange = { text ->
                    if (text.length >= 300) onLengthOver()
                    else onSignalChange(text)
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
            text = stringResource(id = R.string.next),
            onClick = onNextClick,
            enable = signalContent.isNotEmpty()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalContentScreenPreview() {
    var text by rememberSaveable { mutableStateOf("") }
    SsamDTheme {
        SignalContentScreen(
            signalContent = text,
            onNextClick = {},
            onBackClick = {},
            onSignalChange = { text = it }
        )
    }
}
