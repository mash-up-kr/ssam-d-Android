package com.mashup.presentation.signal.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkContentLengthDialog
import com.mashup.presentation.ui.common.KeyLinkTextField
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/20
 */
@Composable
fun SignalContentScreen(
    modifier: Modifier = Modifier,
    navigateToSignalKeyword: () -> Unit = {},
    navigateUp: () -> Unit = {}
) {
    var dialogState by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        KeyLinkToolbar(
            title = {
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.create_signal),
                    style = TextStyle(
                        color = White,
                        fontSize = 14.sp
                    )
                )
            },
            onClickBack = navigateUp
        )
        SignalContent(
            modifier = modifier,
            onNavigate = navigateToSignalKeyword,
            onLengthOver = { dialogState = true }
        )

        if (dialogState) {
            KeyLinkContentLengthDialog(
                onDismissRequest = { /* @TODO */},
                onButtonClick = { dialogState = false }
            )
        }
    }
}

@Composable
fun SignalContent(
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit,
    onLengthOver: () -> Unit,
) {
    var text by rememberSaveable { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        KeyLinkTextField(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            value = text,
            onValueChange = {
                if (it.length >= 300) onLengthOver.invoke()
                text = it
            },
            hint = stringResource(id = R.string.hint_signal_content),
            hintAlign = TextAlign.Start,
            onClickDone = { /*TODO*/ },
            fontSize = 18.sp,
            maxLength = 300
        )
        KeyLinkButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 20.dp)
                .padding(bottom = 48.dp),
            text = stringResource(id = R.string.next),
            onClick = { onNavigate() },
            enable = text.isNotEmpty()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignalContentScreenPreview() {
    SsamDTheme {
        SignalContentScreen()
    }
}
